package es.safareyes.schoolevents.dto.request;

import jakarta.validation.constraints.NotNull;

public record UsuarioInscritoRequest(

        @NotNull(message = "Hay que indicar el usuario")
        Long idUsuario,

        @NotNull(message = "Hay que indicar el evento")
        Long idEvento
) {}