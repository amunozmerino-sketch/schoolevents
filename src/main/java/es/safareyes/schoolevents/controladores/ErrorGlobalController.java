package es.safareyes.schoolevents.controladores;

import es.safareyes.schoolevents.excepciones.RelacionNoEncontradaException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class ErrorGlobalController {
    @ExceptionHandler(RelacionNoEncontradaException.class)
    public ProblemDetail noEncontrada(RelacionNoEncontradaException e) {
        return construir(HttpStatus.FAILED_DEPENDENCY, "Relación no encontrada", e.getMessage());
    }

    private ProblemDetail construir(HttpStatus estado, String titulo, String detalle) {
        ProblemDetail problema = ProblemDetail.forStatusAndDetail(estado, detalle);
        problema.setTitle(titulo);
        problema.setProperty("timestamp", LocalDateTime.now());
        return problema;
    }
}