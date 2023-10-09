package goit.com.shorturlproject.v1.exception;

import org.springframework.data.crossstore.ChangeSetPersister;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super("User not found Exception!");
    }
}
