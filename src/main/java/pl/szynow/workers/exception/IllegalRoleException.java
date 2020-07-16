package pl.szynow.workers.exception;

public class IllegalRoleException extends RuntimeException {
    public IllegalRoleException(String message) {
        super(message);
    }

    public IllegalRoleException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalRoleException(Throwable cause) {
        super(cause);
    }
}
