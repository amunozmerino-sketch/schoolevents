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
    private final UsuarioRepository usuarioRepository;

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

        eventosResumidos.forEach(e -> e.setEnEspera(usuarioInscritoRepository.countUsuarioInscritoByEnEsperaAndEvento_Id(true, e.getId())));
        eventosResumidos.forEach(e -> e.setAforoActual(usuarioInscritoRepository.countUsuarioInscritoByEnEsperaAndEvento_Id(false, e.getId())));

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

        if (nuevoEvento.getFechaHoraIni().isAfter(nuevoEvento.getFechaHoraFin())) {
            throw new ReglaNegocioException("La fecha de inicio no puede ser posterior a la fecha fin");
        }

        if (nuevoEvento.getFechaHoraIni().isBefore(nuevoEvento.getFechaHoraLimite())) {
            throw new ReglaNegocioException("La fecha de inicio no puede ser anterior a la fecha límite.");
        }

        if (dto.idLugar() == null) {
            if (dto.aforoMax() != null) {
                throw new ReglaNegocioException("No puedes poner aforo máximo sin especificar el lugar.");
            }
        } else {
            Lugar lugar = lugarRepository.findById(dto.idLugar()).orElseThrow(() -> new RelacionNoEncontradaException("Lugar no encontrado."));

            if (dto.aforoMax() != null) {
                if (dto.aforoMax() > lugar.getAforoMax()) {
                    throw new ReglaNegocioException("El aforo máximo del evento no puede ser mayor que el del lugar.");
                }
            }

            nuevoEvento.setLugar(lugar);
        }

        if (dto.idCategoria() != null) {
            Categoria categoria = categoriaRepository.findById(dto.idCategoria()).orElseThrow(() -> new RelacionNoEncontradaException("Categoría no encontrada."));
            nuevoEvento.setCategoria(categoria);
        }

        if (dto.idCursos() != null) {
            List<Curso> cursos = dto.idCursos()
                    .stream()
                    .map(c -> cursoRepository.findById(c).orElseThrow(() -> new RelacionNoEncontradaException("Curso no encontrado.")))
                    .toList();

            nuevoEvento.setCursos(cursos);
        }

        if (dto.requisitos() != null) {
            List<Requisito> requisitos = dto.requisitos()
                    .stream()
                    .map(d -> new Requisito(d, nuevoEvento))
                    .toList();

            nuevoEvento.setRequisitos(requisitos);
        }

        Usuario usuarioAutor = usuarioRepository.findById(dto.idUsuarioAutor()).orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado."));

        nuevoEvento.setUsuarioAutor(usuarioAutor);
        nuevoEvento.setEstado(EstadoEvento.EN_BORRADOR);
        eventoRepository.save(nuevoEvento);
        return eventoMapper.aResponse(nuevoEvento);
    }

    public void cancelarEvento(Integer id) {
        Evento evento = eventoRepository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Evento no encontrado."));

        if (evento.getEstado() != EstadoEvento.CANCELADO) {
            evento.setEstado(EstadoEvento.CANCELADO);
            eventoRepository.save(evento);
        } else {
            throw new ReglaNegocioException("El evento ya está cancelado.");
        }
    }

    public void eliminarEvento(Integer id) {
        Evento evento = eventoRepository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Evento no encontrado."));

        if (evento.getEstado() == EstadoEvento.CANCELADO || evento.getEstado() == EstadoEvento.EN_BORRADOR) {
            eventoRepository.deleteById(id);
        } else {
            throw new ReglaNegocioException("No se puede eliminar un evento que está publicado o finalizado.");
        }
    }
}