package goit.com.shorturlproject.v1.user.service.impl;

import goit.com.shorturlproject.v1.registration.exception.UserAlreadyExistException;
import goit.com.shorturlproject.v1.user.dto.User;
import goit.com.shorturlproject.v1.user.repository.UserRepository;
import goit.com.shorturlproject.v1.user.service.UserService;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

//    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User registerNewUserAccount(User user) {
        if (emailExists(user.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + user.getEmail());
        }

//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setConfirmPassword(passwordEncoder.encode(user.getConfirmPassword()));
        return userRepository.save(user);
    }


    @Override
    public void saveRegisteredUser(final User user) {
        userRepository.save(user);
    }

    private boolean emailExists(final String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public Optional<User> getUserByID(final Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User findUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void deleteUser(final User user) {
        userRepository.delete(user);
    }
}
