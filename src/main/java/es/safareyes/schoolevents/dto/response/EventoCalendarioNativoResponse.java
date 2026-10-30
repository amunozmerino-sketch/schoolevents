package es.safareyes.schoolevents.dto.response;

import java.time.LocalDateTime;

public record EventoCalendarioNativoResponse(Long id,
                                             String titulo,
                                             String lugar,
                                             String iconoCategoria,
                                             Integer colorCategoria,
                                             LocalDateTime fechaHoraIni,
                                             LocalDateTime fechaHoraFin) {}