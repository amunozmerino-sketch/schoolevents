package es.safareyes.schoolevents.mappers;

import es.safareyes.schoolevents.dto.response.EventoCalendarioResponse;
import es.safareyes.schoolevents.dto.response.EventoDetalleResponse;
import es.safareyes.schoolevents.dto.response.EventoResumenResponse;
import es.safareyes.schoolevents.modelos.Curso;
import es.safareyes.schoolevents.modelos.Evento;
import es.safareyes.schoolevents.modelos.Requisito;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { CategoriaMapper.class })
public interface EventoMapper {

    @Mapping(target = "lugar", source = "lugar.nombre")
    @Mapping(target = "enEspera", ignore = true)
    EventoDetalleResponse aDetalle(Evento evento);

    @Mapping(target = "colorCategoria", source = "categoria.color")
    @Mapping(target = "enEspera", ignore = true)
    EventoResumenResponse aResumen(Evento evento);

    EventoCalendarioResponse aCalendario(Evento evento);

    default String cursoANombre(Curso curso) {
        return curso.getNombre();
    }

    default String requisitoADescripcion(Requisito requisito) {
        return requisito.getDescripcion();
    }
}