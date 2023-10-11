package goit.com.shorturlproject.v1.user.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super(String.format("User not found with id %d", userId));
    }
}
