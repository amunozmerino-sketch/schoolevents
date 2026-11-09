package es.safareyes.schoolevents.mappers;

import es.safareyes.schoolevents.dto.request.EventoBorradorRequest;
import es.safareyes.schoolevents.dto.response.*;
import es.safareyes.schoolevents.modelos.Evento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { CategoriaMapper.class, CursoMapper.class, FechaMapper.class, RequisitoMapper.class })
public interface EventoMapper {

    @Mapping(target = "lugar", source = "lugar.nombre")
    @Mapping(target = "fechaHoraIni", source = "fechaHoraIni", qualifiedByName = "fechaCompletaAString")
    @Mapping(target = "fechaHoraFin", source = "fechaHoraFin", qualifiedByName = "fechaCompletaAString")
    @Mapping(target = "fechaHoraLimite", source = "fechaHoraLimite", qualifiedByName = "fechaCompletaAString")
    EventoResponse aResponse(Evento evento);

    @Mapping(target = "lugar", source = "lugar.nombre")
    @Mapping(target = "enEspera", ignore = true)
    EventoDetalleResponse aDetalle(Evento evento);

    @Mapping(target = "colorCategoria", source = "categoria.color")
    @Mapping(target = "enEspera", ignore = true)
    EventoResumenResponse aResumen(Evento evento);

    @Mapping(target = "colorCategoria", source = "colorCategoria", qualifiedByName = "colorNativoACodHex")
    EventoCalendarioResponse aCalendario(EventoCalendarioNativoResponse eventoNativo);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lugar", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "cursos", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    @Mapping(target = "requisitos", ignore = true)
    Evento aEntidad(EventoBorradorRequest eventoBorrador);
}