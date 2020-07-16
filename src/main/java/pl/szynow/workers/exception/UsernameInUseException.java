package pl.szynow.workers.exception;

public class UsernameInUseException extends RuntimeException {
    public UsernameInUseException(String message) {
        super(message);
    }

    public UsernameInUseException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameInUseException(Throwable cause) {
        super(cause);
    }
}
