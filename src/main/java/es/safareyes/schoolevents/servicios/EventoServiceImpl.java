package es.safareyes.schoolevents.servicios;

import es.safareyes.schoolevents.dto.request.EventoBorradorRequest;
import es.safareyes.schoolevents.dto.response.EventoCalendarioResponse;
import es.safareyes.schoolevents.dto.response.EventoDetalleResponse;
import es.safareyes.schoolevents.dto.response.EventoResponse;
import es.safareyes.schoolevents.dto.response.EventoResumenResponse;
import es.safareyes.schoolevents.excepciones.RecursoNoEncontradoException;
import es.safareyes.schoolevents.excepciones.ReglaNegocioException;
import es.safareyes.schoolevents.excepciones.RelacionNoEncontradaException;
import es.safareyes.schoolevents.mappers.EventoMapper;
import es.safareyes.schoolevents.modelos.*;
import es.safareyes.schoolevents.modelos.enums.EstadoEvento;
import es.safareyes.schoolevents.repositorios.*;
import es.safareyes.schoolevents.specifications.EventoSpecs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventoServiceImpl implements EventoService {
    private final EventoRepository eventoRepository;

    private final EventoMapper eventoMapper;
    private final CursoRepository cursoRepository;
    private final CategoriaRepository categoriaRepository;
    private final UsuarioInscritoRepository usuarioInscritoRepository;
    private final LugarRepository lugarRepository;

    @Override
    public List<EventoResumenResponse> consultarEventosConFiltros(String titulo, Integer categoria, List<Integer> cursos, LocalDateTime fechaHoraIni, LocalDateTime fechaHoraFin) {
        Specification<Evento> spec = Specification.allOf(
                EventoSpecs.tituloContiene(titulo),
                EventoSpecs.categoriaEquals(categoria),
                EventoSpecs.cursosIn(cursos),
                EventoSpecs.entreFechaHoraIniYFechaHoraFin(fechaHoraIni, fechaHoraFin)
        );

        List<EventoResumenResponse> eventosResumidos = eventoRepository.findAll(spec)
                .stream()
                .map(eventoMapper::aResumen)
                .toList();

        eventosResumidos.forEach(e-> e.setEnEspera(usuarioInscritoRepository.countUsuarioInscritoByEnEsperaAndEvento_Id(true, e.getId())));

        return eventosResumidos;
    }

    @Override
    public EventoDetalleResponse consultarDetallesEvento(Integer id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Evento no encontrado."));
        return eventoMapper.aDetalle(evento);
    }

    @Override
    public List<EventoCalendarioResponse> consultarEventosDelMesPorDia(Integer mes, Integer ano) {
        return eventoRepository.eventosDelMesPorDia(mes, ano)
                .stream()
                .map(eventoMapper::aCalendario)
                .toList();
    }

    @Override
    public List<EventoResumenResponse> consultarListaEventosporCategoria(Integer idCategoria) {
        List<EventoResumenResponse> eventosResumidos = eventoRepository.findAllByCategoria_IdOrderByFechaHoraIni(idCategoria)
                .stream()
                .map(eventoMapper::aResumen)
                .toList();

        eventosResumidos.forEach(e-> e.setEnEspera(usuarioInscritoRepository.countUsuarioInscritoByEnEsperaAndEvento_Id(true, e.getId())));

        return eventosResumidos;
    }

    public EventoResponse crearEvento(EventoBorradorRequest dto) {
        Evento nuevoEvento = eventoMapper.aEntidad(dto);

        Lugar lugar = lugarRepository.findById(dto.idLugar()).orElseThrow(() -> new RelacionNoEncontradaException("Lugar no encontrado."));

        Categoria categoria = categoriaRepository.findById(dto.idCategoria()).orElseThrow(() -> new RelacionNoEncontradaException("Categoría no encontrada."));

        List<Curso> cursos = dto.idCursos()
                                .stream()
                                .map(c -> cursoRepository.findById(c).orElseThrow(() -> new RelacionNoEncontradaException("Curso no encontrado.")))
                                .toList();

        List<Requisito> requisitos = dto.requisitos()
                                        .stream()
                                        .map(d -> new Requisito(d, nuevoEvento))
                                        .toList();

        nuevoEvento.setLugar(lugar);
        nuevoEvento.setCategoria(categoria);
        nuevoEvento.setCursos(cursos);
        nuevoEvento.setEstado(EstadoEvento.EN_BORRADOR);
        nuevoEvento.setRequisitos(requisitos);
        eventoRepository.save(nuevoEvento);
        return eventoMapper.aResponse(nuevoEvento);
    }

    public void cancelarEvento(Integer id) {
        Evento evento = eventoRepository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Evento no encontrado"));

        if (evento.getEstado() != EstadoEvento.CANCELADO) {
            evento.setEstado(EstadoEvento.CANCELADO);
            eventoRepository.save(evento);
        } else {
            throw new ReglaNegocioException("El evento ya está cancelado.");
        }
    }

    public void eliminarEvento(Integer id) {
        Evento evento = eventoRepository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Evento no encontrado"));

        if (evento.getEstado() == EstadoEvento.CANCELADO || evento.getEstado() == EstadoEvento.EN_BORRADOR) {
            eventoRepository.deleteById(id);
        } else {
            throw new ReglaNegocioException("No se puede eliminar un evento que está publicado o finalizado.");
        }
    }
}