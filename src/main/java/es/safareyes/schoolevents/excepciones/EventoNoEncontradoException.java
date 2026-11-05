package es.safareyes.schoolevents.excepciones;

public class EventoNoEncontradoException extends RuntimeException {
    public EventoNoEncontradoException(String message) {
        super(message);
    }
}
