package es.safareyes.schoolevents.mappers;

import es.safareyes.schoolevents.dto.request.EventoBorradorRequest;
import es.safareyes.schoolevents.dto.response.*;
import es.safareyes.schoolevents.modelos.Evento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Mapper(componentModel = "spring",
        imports = LocalTime.class,
        uses = { CategoriaMapper.class, CursoMapper.class, RequisitoMapper.class })
public interface EventoMapper {
    String FORMATO_TIMESTAMP = "dd/MM/yyyy HH:mm";
    String FORMATO_FECHA = "dd/MM/yyyy";
    String FORMATO_HORA = "HH:mm";

    @Mapping(target = "lugar", source = "lugar.nombre")
    @Mapping(target = "fechaHoraIni", source = "fechaHoraIni", dateFormat = FORMATO_TIMESTAMP)
    @Mapping(target = "fechaHoraFin", source = "fechaHoraFin", dateFormat = FORMATO_TIMESTAMP)
    @Mapping(target = "fechaHoraLimite", source = "fechaHoraLimite", dateFormat = FORMATO_TIMESTAMP)
    EventoResponse aResponse(Evento evento);

    @Mapping(target = "lugar", source = "lugar.nombre")
    @Mapping(target = "fechaIni", source = "fechaHoraIni", dateFormat = FORMATO_FECHA)
    @Mapping(target = "fechaLimite", source = "fechaHoraLimite", dateFormat = FORMATO_FECHA)
    @Mapping(target = "horaIni", source = "fechaHoraIni", dateFormat = FORMATO_HORA)
    @Mapping(target = "horaFin", source = "fechaHoraFin", dateFormat = FORMATO_HORA)
    @Mapping(target = "aforoActual", ignore = true)
    @Mapping(target = "enEspera", ignore = true)
    EventoDetalleResponse aDetalle(Evento evento);

    @Mapping(target = "colorCategoria", source = "categoria.color")
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
            expression = "java(completarFechas(eventoBorrador.fechaIni(), request.horaIni()))")
    @Mapping(target = "fechaHoraFin",
            expression = "java(completarFechas(eventoBorrador.fechaIni(), request.horaFin()))")
    @Mapping(target = "fechaHoraLimite",
            expression = "java(completarFechas(eventoBorrador.fechaLimite(), LocalTime.MIN))")
    @Mapping(target = "lugar", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "cursos", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    @Mapping(target = "requisitos", ignore = true)
    @Mapping(target = "usuarioAutor", ignore = true)
    Evento aEntidad(EventoBorradorRequest eventoBorrador);

    default LocalDateTime completarFechas(LocalDate fecha, LocalTime hora) {
        return (fecha == null || hora == null) ? null : LocalDateTime.of(fecha, hora);
    }
}