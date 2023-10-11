package goit.com.shorturlproject.v1.user.service.impl;

import goit.com.shorturlproject.v1.user.dto.User;
import goit.com.shorturlproject.v1.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUser() {
        User userToSave = new User();
        userToSave.setLogin("TestUser");

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setLogin("TestUser");

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        User resultUser = userService.save(userToSave);


        assertEquals(savedUser, resultUser);
    }

    @Test
    void testFindUserById_UserFound() {
        Long userId = 1L;
        User expectedUser = new User();
        expectedUser.setId(userId);
        expectedUser.setLogin("TestUser");

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(expectedUser));

        Optional<User> resultUser = userService.findById(userId);

        assertEquals(Optional.of(expectedUser), resultUser);
    }

    @Test
    void testFindUserById_UserNotFound() {
        Long userId = 1L;

        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<User> resultUser = userService.findById(userId);

        assertEquals(Optional.empty(), resultUser);
    }
}
