package goit.com.shorturlproject.v1.user.service;

import goit.com.shorturlproject.v1.user.dto.User;

import java.util.Optional;

public interface UserService {
    User save(User user);

    Optional<User> findById(Long id);
}
