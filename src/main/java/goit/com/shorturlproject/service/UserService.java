package goit.com.shorturlproject.service;

import goit.com.shorturlproject.dto.UserDto;
import goit.com.shorturlproject.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {
    User registerNewUserAccount(UserDto accountDto);
    void saveRegisteredUser(User user);

    void deleteUser(User user);
    User findUserByEmail(String email);
    Optional<User> getUserByID(long id);


    //ResponseEntity<?> saveUser(User user);
   // ResponseEntity<?> confirmEmail(String confirmationToken);
}