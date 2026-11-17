package es.safareyes.schoolevents.mappers;

import es.safareyes.schoolevents.dto.response.CategoriaResponse;
import es.safareyes.schoolevents.dto.response.CategoriaSimplificadaResponse;
import es.safareyes.schoolevents.modelos.Categoria;
import es.safareyes.schoolevents.modelos.enums.ColorCategoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    @Mapping(target = "color", source = "color.codHex")
    CategoriaResponse aResumen(Categoria categoria);

    @Mapping(target = "color", source = "color.codHex")
    CategoriaSimplificadaResponse aSimplificada(Categoria categoria);

    @Named("colorNativoACodHex")
    default String colorNumACodHex(Integer colorNum) {
        ColorCategoria color = ColorCategoria.posicionAColor(colorNum);
        return color.getCodHex();
    }
}