package goit.com.shorturlproject.v1.exception;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String message) {
        super("User not found Exception!");
    }
}
