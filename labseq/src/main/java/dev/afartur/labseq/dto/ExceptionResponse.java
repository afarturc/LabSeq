package dev.afartur.labseq.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Record for an Exception response.
 * @param timestamp local date and time information of the response.
 * @param message error message of the exception.
 */
public record ExceptionResponse(
        LocalDateTime timestamp,
        String message
) {
}
