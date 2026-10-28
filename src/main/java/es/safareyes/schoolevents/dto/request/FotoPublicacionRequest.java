package es.safareyes.schoolevents.dto.request;

import jakarta.validation.constraints.NotBlank;

public record FotoPublicacionRequest(

        @NotBlank(message = "El enlace a la imagen es obligatorio")
        String enlace
) {}