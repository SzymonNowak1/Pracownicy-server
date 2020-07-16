package pl.szynow.workers.exception;

public class EmailInUseException extends RuntimeException {
    public EmailInUseException(String message) {
        super(message);
    }

    public EmailInUseException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailInUseException(Throwable cause) {
        super(cause);
    }
}
