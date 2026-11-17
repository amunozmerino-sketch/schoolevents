package es.safareyes.schoolevents.mappers;

import es.safareyes.schoolevents.dto.response.CursoResponse;
import es.safareyes.schoolevents.modelos.Curso;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CursoMapper {

    CursoResponse aResumen(Curso curso);

    List<CursoResponse> aListaResumen(List<Curso> cursos);
}