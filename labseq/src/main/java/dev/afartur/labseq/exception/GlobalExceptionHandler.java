package dev.afartur.labseq.exception;

import dev.afartur.labseq.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

/**
 * Class for handling custom exceptions.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Returns the response for invalid input exception.
     * @param ex invalid input exception.
     * @return entity for an exception response.
     */
    @ExceptionHandler(InputException.class)
    public ResponseEntity<ExceptionResponse> handleInputException(final InputException ex) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(LocalDateTime.now(), ex.getMessage()));
    }
}
