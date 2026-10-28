package es.safareyes.schoolevents.dto.response;

public record UsuarioPublicacionResponse(Long id,
                                         String nombre,
                                         String apellidos,
                                         Integer rol,
                                         String curso) {}