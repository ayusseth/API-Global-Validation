package main.controller;

import lombok.extern.slf4j.Slf4j;
import main.exception.UserNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        log.error("Returning UserNotFoundException");
        System.out.println(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // Validation errors (@Valid on body)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationErrors(MethodArgumentNotValidException ex) {
        log.error("Returning MethodArgumentNotValidException");

        StringBuilder sb = new StringBuilder("Validation failed: ");
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> sb.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage()).append("; "));
        System.out.println(sb);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
    }


    // Duplicate entry / Unique constraint
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        log.error("Returning DataIntegrityViolationException");

        System.out.println("Duplicate email detected");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Duplicate email not allowed");
    }


}
