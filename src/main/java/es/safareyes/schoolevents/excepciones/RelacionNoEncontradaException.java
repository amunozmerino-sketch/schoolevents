package es.safareyes.schoolevents.excepciones;

public class RelacionNoEncontradaException extends RuntimeException {
    public RelacionNoEncontradaException(String message) {
        super(message);
    }
}