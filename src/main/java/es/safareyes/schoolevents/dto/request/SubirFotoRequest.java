package es.safareyes.schoolevents.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record SubirFotoRequest(
        @NotNull(message = "Hay que indicar la publicación")
        Integer idPublicacion,

        @NotEmpty(message = "Hay que indicar las fotos a subir")
        List<@NotNull(message = "Hay que indicar las fotos a subir") Integer> fotos
) {}