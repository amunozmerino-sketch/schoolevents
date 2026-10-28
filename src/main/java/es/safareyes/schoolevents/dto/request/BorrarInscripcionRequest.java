package es.safareyes.schoolevents.dto.request;

import jakarta.validation.constraints.NotNull;

public record BorrarInscripcionRequest(

        @NotNull(message = "Hay que indicar la inscripción a borrar")
        Long idInscripcion
) {}