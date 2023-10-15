package goit.com.shorturlproject.v1.user.service;

import goit.com.shorturlproject.v1.user.dto.User;

import java.util.Optional;

public interface UserService {
    User save(User user);

    Optional<User> findById(Long id);

    User registerNewUserAccount(User user);

    void saveRegisteredUser(User user);

    void deleteUser(User user);

    User findUserByEmail(String email);

    Optional<User> getUserByID(Long id);

}
