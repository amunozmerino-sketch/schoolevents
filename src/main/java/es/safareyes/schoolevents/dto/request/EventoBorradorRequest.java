package es.safareyes.schoolevents.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record EventoBorradorRequest(
        @NotBlank(message = "El título es obligatorio")
        String titulo,

        String descripcion,

        String foto,

        Integer idLugar,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate fechaIni,

        LocalTime horaIni,

        LocalTime horaFin,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate fechaLimite,

        Integer aforoMax,

        List<Integer> idCursos,

        Integer idCategoria,

        List<String> requisitos,

        @NotNull(message = "El evento tiene que tener un autor")
        Integer idUsuarioAutor
) {}