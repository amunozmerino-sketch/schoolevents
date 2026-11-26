package es.safareyes.schoolevents.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record EventoResponse(
        String titulo,

        String descripcion,

        String foto,

        Integer idLugar,

        LocalDate fechaIni,

        LocalTime horaIni,

        LocalTime horaFin,

        LocalDate fechaLimite,

        Integer aforoMax,

        List<Integer> idCursos,

        Integer idCategoria,

        List<String> requisitos,

        Integer idUsuarioAutor
) {}