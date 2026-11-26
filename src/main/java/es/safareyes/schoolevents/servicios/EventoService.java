package es.safareyes.schoolevents.servicios;

import es.safareyes.schoolevents.dto.request.EventoBorradorRequest;
import es.safareyes.schoolevents.dto.request.EventoEstadoRequest;
import es.safareyes.schoolevents.dto.response.EventoCalendarioResponse;
import es.safareyes.schoolevents.dto.response.EventoDetalleResponse;
import es.safareyes.schoolevents.dto.response.EventoResponse;
import es.safareyes.schoolevents.dto.response.EventoResumenResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface EventoService {
    List<EventoResumenResponse> consultarEventosConFiltros(String titulo, Integer categoria, List<Integer> cursos, String fechaHoraIni, String fechaHoraFin);
    List<EventoCalendarioResponse> consultarEventosDelMesPorDia(Integer mes, Integer ano);
    List<EventoResumenResponse> consultarListaEventosporCategoria(Integer idCategoria);
    List<EventoResumenResponse> consultarEventosProximos();
    EventoResponse crearEvento(EventoBorradorRequest dto);
    EventoDetalleResponse consultarDetallesEvento(Integer id);
    void cambiarEstado(Integer id, EventoEstadoRequest dto);
    void eliminarEvento(Integer id);
}