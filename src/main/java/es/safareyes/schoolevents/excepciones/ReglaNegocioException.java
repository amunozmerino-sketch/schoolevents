package es.safareyes.schoolevents.excepciones;

public class ReglaNegocioException extends RuntimeException {
    public ReglaNegocioException(String message) {
        super(message);
    }
}