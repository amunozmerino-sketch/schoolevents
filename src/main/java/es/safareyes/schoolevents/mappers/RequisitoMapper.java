package es.safareyes.schoolevents.mappers;

import es.safareyes.schoolevents.dto.response.RequisitoResumenResponse;
import es.safareyes.schoolevents.modelos.Requisito;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RequisitoMapper {

    RequisitoResumenResponse aResumen(Requisito requisito);

    List<RequisitoResumenResponse> aListaResumen(List<Requisito> requisitos);
}