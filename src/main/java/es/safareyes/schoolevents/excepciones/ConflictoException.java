package es.safareyes.schoolevents.excepciones;

public class ConflictoException extends RuntimeException {
    public ConflictoException(String message) {
        super(message);
    }
}