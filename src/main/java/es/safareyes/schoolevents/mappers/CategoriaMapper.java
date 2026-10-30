package es.safareyes.schoolevents.mappers;

import es.safareyes.schoolevents.dto.response.CategoriaResumenResponse;
import es.safareyes.schoolevents.dto.response.CategoriaSimplificadaResponse;
import es.safareyes.schoolevents.modelos.Categoria;
import es.safareyes.schoolevents.modelos.enums.ColorCategoria;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    CategoriaResumenResponse aResumen(Categoria categoria);

    CategoriaSimplificadaResponse aSimplificada(Categoria categoria);

    default String colorACodHex(ColorCategoria color) {
        return color == null ? null : color.getCodHex();
    }

    @Named("colorNativoACodHex")
    default String colorNumACodHex(Integer colorNum) {
        ColorCategoria color = ColorCategoria.values()[colorNum];

        return color == null ? null : color.getCodHex();
    }
}