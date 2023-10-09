package goit.com.shorturlproject.v1.service;

// The service interface in this module is UserService. This interface declares
// two methods: saveUser() to store a User object in the database.
// The second method is getUserByNameAndPassword() to retrieve a user with
// the given user name and password.

import goit.com.shorturlproject.v1.exception.UserNotFoundException;
import goit.com.shorturlproject.v1.user.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public void saveUser(User user);
    public User getUserByNameAndPassword(String name, String password) throws UserNotFoundException;
}
