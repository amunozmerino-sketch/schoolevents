package es.safareyes.schoolevents.dto.response;

public record UsuarioInscritoResponse(Long id, UsuarioListaResponse usuario, boolean enEspera) {}