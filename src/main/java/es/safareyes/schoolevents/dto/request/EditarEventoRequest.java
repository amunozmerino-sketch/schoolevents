package es.safareyes.schoolevents.dto.request;

import java.util.List;

public record EditarEventoRequest(
        String titulo,
        String descripcion,
        Integer idLugar,
        String fechaHoraIni,
        String fechaHoraFin,
        String fechaHoraLimite,
        Integer aforoMax,
        List<Integer> idCursos,
        Integer idCategoria,
        List<Integer> idRequisitos
) {}