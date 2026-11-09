package es.safareyes.schoolevents.controladores;

import es.safareyes.schoolevents.excepciones.RecursoNoEncontradoException;
import es.safareyes.schoolevents.excepciones.ReglaNegocioException;
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

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ProblemDetail noEncontrado(RecursoNoEncontradoException e) {
        return construir(HttpStatus.NOT_FOUND, "Recurso no encontrado", e.getMessage());
    }

    public ProblemDetail reglaNegocio(ReglaNegocioException e) {
        return construir(HttpStatus.UNPROCESSABLE_CONTENT, "Regla de negocio incumplida", e.getMessage());
    }

    private ProblemDetail construir(HttpStatus estado, String titulo, String detalle) {
        ProblemDetail problema = ProblemDetail.forStatusAndDetail(estado, detalle);
        problema.setTitle(titulo);
        problema.setProperty("timestamp", LocalDateTime.now());
        return problema;
    }
}