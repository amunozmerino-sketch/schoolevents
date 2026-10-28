package es.safareyes.schoolevents.dto.response;

import java.time.LocalDateTime;

public record EventoResumenResponse(Long id,
                                    String titulo,
                                    Integer aforoActual,
                                    Integer aforoMax,
                                    Integer enEspera,
                                    String colorCategoria,
                                    LocalDateTime fechaHoraIni,
                                    LocalDateTime fechaHoraFin,
                                    LocalDateTime fechaHoraLimite) {}