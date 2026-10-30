package es.safareyes.schoolevents.dto.response;

import java.time.LocalDateTime;

public record EventoCalendarioResponse(Long id,
                                       String titulo,
                                       String lugar,
                                       String iconoCategoria,
                                       String colorCategoria,
                                       LocalDateTime fechaHoraIni,
                                       LocalDateTime fechaHoraFin) {}