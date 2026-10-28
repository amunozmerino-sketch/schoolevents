package es.safareyes.schoolevents.dto.request;

import jakarta.validation.constraints.NotNull;

public record EventoEstadoRequest(

        @NotNull(message = "Hay que indicar el evento a cambiar")
        Long id,

        @NotNull(message = "Hay que indicar el estado nuevo")
        Integer estado
) {}