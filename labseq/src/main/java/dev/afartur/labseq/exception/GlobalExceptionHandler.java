package dev.afartur.labseq.exception;

import dev.afartur.labseq.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InputException.class)
    public ResponseEntity<ExceptionResponse> handleInputException(final InputException ex) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(LocalDateTime.now(), ex.getMessage()));
    }
}
