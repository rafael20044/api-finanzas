package dev.rafaelbarragan.api.finanza.controller;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import dev.rafaelbarragan.api.finanza.exception.CorreoException;
import dev.rafaelbarragan.api.finanza.exception.Exception;
import dev.rafaelbarragan.api.finanza.exception.ExistenciaException;
import dev.rafaelbarragan.api.finanza.exception.TransactionException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ExistenciaException.class)
    public ResponseEntity<Exception> errorExistencia(ExistenciaException e){
        var ex = new Exception(e.getMessage(), 404);
        return ResponseEntity.status(404).body(ex);
    }

    @ExceptionHandler(CorreoException.class)
    public ResponseEntity<Exception> errorExistencia(CorreoException e){
        var ex = new Exception(e.getMessage(), 400);
        return ResponseEntity.badRequest().body(ex);
    }

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<Exception> errorExistencia(TransactionException e){
        var ex = new Exception(e.getMessage(), 400);
        return ResponseEntity.badRequest().body(ex);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Exception> errorExistencia(ConstraintViolationException e){
        String violations = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage) .collect(Collectors.joining(", "));
        var ex = new Exception(violations, 400);
        return ResponseEntity.badRequest().body(ex);
    }

    @ExceptionHandler(JWTCreationException.class)
    public ResponseEntity<Exception> errorCrearToken(JWTCreationException e){
        var ex = new Exception(e.getMessage(), 400);
        return ResponseEntity.badRequest().body(ex);
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<Exception> errorVerificarToken(JWTVerificationException e){
        var ex = new Exception(e.getMessage(), 400);
        return ResponseEntity.badRequest().body(ex);
    }
}
