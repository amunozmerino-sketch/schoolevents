package es.safareyes.schoolevents.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record PublicacionConFotosResponse(UsuarioPublicacionResponse usuario,
                                          String texto,
                                          LocalDateTime fechaHora,
                                          List<FotoPublicacionResumenResponse> fotos) {}