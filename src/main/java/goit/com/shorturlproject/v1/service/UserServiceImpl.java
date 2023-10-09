package goit.com.shorturlproject.v1.service;

import goit.com.shorturlproject.v1.exception.UserNotFoundException;
import goit.com.shorturlproject.v1.repo.UserRepository;
import goit.com.shorturlproject.v1.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserByNameAndPassword(String email, String password) throws UserNotFoundException {
        User user = userRepository.findByUserNameAndPassword(email, password);
        if(user == null){
            throw new UserNotFoundException("Invalid id and password");
        }
        return user;
    }
}
