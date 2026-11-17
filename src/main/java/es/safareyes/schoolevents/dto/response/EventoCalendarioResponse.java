package es.safareyes.schoolevents.dto.response;

public record EventoCalendarioResponse(Integer id,
                                       String titulo,
                                       String lugar,
                                       String iconoCategoria,
                                       String colorCategoria,
                                       String fechaIni,
                                       String horaIni,
                                       String horaFin) {}