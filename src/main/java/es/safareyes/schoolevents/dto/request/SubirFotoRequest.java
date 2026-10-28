package es.safareyes.schoolevents.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record SubirFotoRequest(
        @NotNull(message = "Hay que indicar la publicación")
        Long idPublicacion,

        @NotEmpty(message = "Hay que indicar las fotos a subir")
        List<@NotNull Integer> fotos
) {}