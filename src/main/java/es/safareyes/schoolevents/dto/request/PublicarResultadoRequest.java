package es.safareyes.schoolevents.dto.request;

import jakarta.validation.constraints.NotNull;

public record PublicarResultadoRequest(

        @NotNull(message = "Hay que indicar el evento")
        Long idEvento,

        @NotNull(message = "Hay que indicar la publicación")
        Long idPublicacion
) {}