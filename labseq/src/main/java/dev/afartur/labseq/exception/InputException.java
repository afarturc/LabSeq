package dev.afartur.labseq.exception;

/**
 * Class for invalid input exception.
 * Extends RuntimeException.
 */
public class InputException extends RuntimeException {
    public InputException() {
    }

    public InputException(String message) {
        super(message);
    }

    public InputException(String message, Throwable cause) {
        super(message, cause);
    }

    public InputException(Throwable cause) {
        super(cause);
    }
}
