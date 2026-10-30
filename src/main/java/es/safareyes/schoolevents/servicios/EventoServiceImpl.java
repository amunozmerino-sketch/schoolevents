package es.safareyes.schoolevents.servicios;

import es.safareyes.schoolevents.dto.response.EventoCalendarioResponse;
import es.safareyes.schoolevents.dto.response.EventoDetalleResponse;
import es.safareyes.schoolevents.dto.response.EventoResumenResponse;
import es.safareyes.schoolevents.mappers.EventoMapper;
import es.safareyes.schoolevents.modelos.Evento;
import es.safareyes.schoolevents.repositorios.EventoRepository;
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

    @Override
    public List<EventoResumenResponse> consultarEventosConFiltros(String titulo, Integer categoria, List<Integer> cursos, LocalDateTime fechaHoraIni, LocalDateTime fechaHoraFin) {
        Specification<Evento> spec = Specification.allOf(
                EventoSpecs.tituloContiene(titulo),
                EventoSpecs.categoriaEquals(categoria),
                EventoSpecs.cursosIn(cursos),
                EventoSpecs.entreFechaHoraIniYFechaHoraFin(fechaHoraIni, fechaHoraFin)
        );

        return eventoRepository.findAll(spec)
                .stream()
                .map(eventoMapper::aResumen)
                .toList();
    }

    @Override
    public EventoDetalleResponse consultarDetallesEvento(Integer id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow();
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
        return eventoRepository.findAllByCategoria_IdOrderByFechaHoraIni(idCategoria)
                .stream()
                .map(eventoMapper::aResumen)
                .toList();
    }
}