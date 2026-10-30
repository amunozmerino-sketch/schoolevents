package es.safareyes.schoolevents.servicios;

import es.safareyes.schoolevents.dto.response.EventoCalendarioResponse;
import es.safareyes.schoolevents.dto.response.EventoDetalleResponse;
import es.safareyes.schoolevents.dto.response.EventoResumenResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface EventoService {
    List<EventoResumenResponse> consultarEventosConFiltros(String titulo, Integer categoria, List<Integer> cursos, LocalDateTime fechaHoraIni, LocalDateTime fechaHoraFin);
    EventoDetalleResponse consultarDetallesEvento(Integer id);
    List<EventoCalendarioResponse> consultarEventosDelMesPorDia(Integer mes, Integer ano);
    List<EventoResumenResponse> consultarListaEventosporCategoria(Integer idCategoria);
}