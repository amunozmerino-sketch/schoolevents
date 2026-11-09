package es.safareyes.schoolevents.dto.response;

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
        List<CursoResumenResponse> cursos,
        CategoriaResumenResponse categoria,
        List<RequisitoResumenResponse> requisitos
) {}