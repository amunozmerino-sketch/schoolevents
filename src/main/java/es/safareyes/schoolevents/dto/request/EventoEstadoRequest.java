package es.safareyes.schoolevents.dto.request;

import jakarta.validation.constraints.NotBlank;

public record EventoEstadoRequest(
        @NotBlank(message = "Hay que indicar el estado nuevo")
        String estado
) {}