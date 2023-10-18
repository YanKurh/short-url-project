package goit.com.shorturlproject.v1.registration.exception;

public final class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException() {
        super();
    }

    public UserAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAlreadyExistException( String message) {
        super(message);
    }

    public UserAlreadyExistException( Throwable cause) {
        super(cause);
    }
}
