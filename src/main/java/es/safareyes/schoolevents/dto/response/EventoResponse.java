package es.safareyes.schoolevents.dto.response;

import es.safareyes.schoolevents.modelos.Usuario;

import java.util.List;

public record EventoResponse(
        Integer id,
        String titulo,
        String descripcion,
        String lugar,
        String fechaHoraIni,
        String fechaHoraFin,
        String fechaHoraLimite,
        Integer aforoMax,
        List<CursoResponse> cursos,
        CategoriaResponse categoria,
        List<RequisitoResumenResponse> requisitos,
        Usuario usuarioAutor
) {}