package es.safareyes.schoolevents.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record EventoDetalleResponse(Long id,
                                    String titulo,
                                    String descripcion,
                                    String lugar,
                                    LocalDateTime fechaHoraIni,
                                    LocalDateTime fechaHoraFin,
                                    LocalDateTime fechaHoraLimite,
                                    Integer aforoMax,
                                    Integer aforoActual,
                                    Integer enEspera,
                                    List<String> cursos,
                                    CategoriaResumenResponse categoria,
                                    List<String> requisitos
) {}