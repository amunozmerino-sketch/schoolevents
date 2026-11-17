package es.safareyes.schoolevents.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record EventoBorradorRequest(
        @NotBlank(message = "El título es obligatorio.")
        String titulo,

        String descripcion,

        Integer idLugar,

        @NotNull(message = "La fecha de inicio es obligatoria.")
        @FutureOrPresent
        LocalDateTime fechaHoraIni,

        @NotNull
        @FutureOrPresent
        LocalDateTime fechaHoraFin,

        @NotNull
        @FutureOrPresent
        LocalDateTime fechaHoraLimite,

        Integer aforoMax,

        List<Integer> idCursos,

        Integer idCategoria,

        List<String> requisitos,

        @NotNull(message = "El evento tiene que tener un autor.")
        Integer idUsuarioAutor
) {}