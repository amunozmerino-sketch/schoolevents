package es.safareyes.schoolevents.dto.response;

import java.time.LocalDateTime;

public record NotificacionResponse(Long id,
                                   String mensaje,
                                   LocalDateTime fechaHora,
                                   boolean leida) {}