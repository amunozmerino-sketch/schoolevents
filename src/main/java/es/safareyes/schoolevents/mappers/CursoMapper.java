package es.safareyes.schoolevents.mappers;

import es.safareyes.schoolevents.dto.response.CursoResumenResponse;
import es.safareyes.schoolevents.modelos.Curso;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CursoMapper {

    CursoResumenResponse aResumen(Curso curso);

    List<CursoResumenResponse> aListaResumen(List<Curso> cursos);
}