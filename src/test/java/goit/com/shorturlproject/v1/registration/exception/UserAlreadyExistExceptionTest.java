package goit.com.shorturlproject.v1.registration.exception;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserAlreadyExistExceptionTest {

    @Test
    public void testUserAlreadyExistExceptionWithoutMessageAndCause() {
        UserAlreadyExistException exception = new UserAlreadyExistException();
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testUserAlreadyExistExceptionWithMessage() {
        String message = "User already exists";
        UserAlreadyExistException exception = new UserAlreadyExistException(message);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }


    @Test
    public void testUserAlreadyExistExceptionWithMessageAndCause() {
        String message = "User already exists";
        Throwable cause = new RuntimeException("Root cause");
        UserAlreadyExistException exception = new UserAlreadyExistException(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
