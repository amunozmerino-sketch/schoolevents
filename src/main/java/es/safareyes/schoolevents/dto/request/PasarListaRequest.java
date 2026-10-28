package es.safareyes.schoolevents.dto.request;

import jakarta.validation.constraints.NotNull;

public record PasarListaRequest(

        @NotNull(message = "Hay que indicar la inscripción")
        Long idInscripcion
) {}