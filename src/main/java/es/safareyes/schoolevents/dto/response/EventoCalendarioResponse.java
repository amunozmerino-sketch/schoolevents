package es.safareyes.schoolevents.dto.response;

import java.time.LocalDateTime;

public record EventoCalendarioResponse(Long id,
                                       String titulo,
                                       String lugar,
                                       CategoriaSimplificadaResponse categoria,
                                       LocalDateTime fechaHoraIni,
                                       LocalDateTime fechaHoraFin) {}