package es.safareyes.schoolevents.mappers;

import es.safareyes.schoolevents.dto.request.EditarEventoRequest;
import es.safareyes.schoolevents.dto.request.EventoBorradorRequest;
import es.safareyes.schoolevents.dto.response.*;
import es.safareyes.schoolevents.modelos.Evento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        imports = LocalTime.class,
        uses = { CategoriaMapper.class, CursoMapper.class, RequisitoMapper.class })

public interface EventoMapper {
    String FORMATO_FECHA = "dd/MM/yyyy";
    String FORMATO_HORA = "HH:mm";

    EventoResponse aResponse(Evento evento);

    @Mapping(target = "lugar", source = "lugar.nombre")
    @Mapping(target = "fechaIni", source = "fechaHoraIni", dateFormat = FORMATO_FECHA)
    @Mapping(target = "fechaLimite", source = "fechaHoraLimite", dateFormat = FORMATO_FECHA)
    @Mapping(target = "horaIni", source = "fechaHoraIni", dateFormat = FORMATO_HORA)
    @Mapping(target = "horaFin", source = "fechaHoraFin", dateFormat = FORMATO_HORA)
    @Mapping(target = "aforoActual", ignore = true)
    @Mapping(target = "enEspera", ignore = true)
    EventoDetalleResponse aDetalle(Evento evento);

    @Mapping(target = "colorCategoria", source = "categoria.color.codHex")
    @Mapping(target = "fechaIni", source = "fechaHoraIni", dateFormat = FORMATO_FECHA)
    @Mapping(target = "fechaLimite", source = "fechaHoraLimite", dateFormat = FORMATO_FECHA)
    @Mapping(target = "horaIni", source = "fechaHoraIni", dateFormat = FORMATO_HORA)
    @Mapping(target = "horaFin", source = "fechaHoraFin", dateFormat = FORMATO_HORA)
    @Mapping(target = "aforoActual", ignore = true)
    @Mapping(target = "enEspera", ignore = true)
    EventoResumenResponse aResumen(Evento evento);

    @Mapping(target = "colorCategoria", source = "colorCategoria", qualifiedByName = "colorNativoACodHex")
    @Mapping(target = "fechaIni", source = "fechaHoraIni", dateFormat = FORMATO_FECHA)
    @Mapping(target = "horaIni", source = "fechaHoraIni", dateFormat = FORMATO_HORA)
    @Mapping(target = "horaFin", source = "fechaHoraFin", dateFormat = FORMATO_HORA)
    EventoCalendarioResponse aCalendario(EventoCalendarioNativoResponse eventoNativo);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaHoraIni",
            expression = "java(completarFechas(borrador.fechaIni(), borrador.horaIni()))")
    @Mapping(target = "fechaHoraFin",
            expression = "java(completarFechas(borrador.fechaIni(), borrador.horaFin()))")
    @Mapping(target = "fechaHoraLimite",
            expression = "java(completarFechas(borrador.fechaLimite(), LocalTime.MIN))")
    @Mapping(target = "lugar", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "cursos", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    @Mapping(target = "requisitos", ignore = true)
    @Mapping(target = "usuarioAutor", ignore = true)
    Evento aEntidad(EventoBorradorRequest borrador);

    EventoResponse

    default LocalDateTime completarFechas(LocalDate fecha, LocalTime hora) {
        return (fecha == null || hora == null) ? null : LocalDateTime.of(fecha, hora);
    }
}