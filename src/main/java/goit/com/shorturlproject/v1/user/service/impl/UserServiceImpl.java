package goit.com.shorturlproject.v1.user.service.impl;

import goit.com.shorturlproject.v1.user.dto.User;
import goit.com.shorturlproject.v1.user.repository.UserRepository;
import goit.com.shorturlproject.v1.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}
