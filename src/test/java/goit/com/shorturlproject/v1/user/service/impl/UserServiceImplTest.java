package goit.com.shorturlproject.v1.user.service.impl;

import goit.com.shorturlproject.v1.ITestContainer;
import goit.com.shorturlproject.v1.registration.exception.UserAlreadyExistException;
import goit.com.shorturlproject.v1.user.dto.User;
import goit.com.shorturlproject.v1.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class UserServiceImplTest implements ITestContainer {

    @Mock
    private UserRepository userRepository;
    @Mock
    private  PasswordEncoder passwordEncoder;

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
    @Test
    void testRegisterNewUserAccount_UserDoesNotExist() {

        User newUser = new User();
        newUser.setEmail("newuser@example.com");
        newUser.setPassword("Password1");

        when(userRepository.findByEmail(newUser.getEmail())).thenReturn(null);
        when(passwordEncoder.encode(newUser.getPassword())).thenReturn("EncodedPassword1");
        when(userRepository.save(newUser)).thenReturn(newUser);

        User registeredUser = userService.registerNewUserAccount(newUser);

        assertEquals(newUser, registeredUser);
    }

//    @Test
    void testRegisterNewUserAccount_UserAlreadyExists() {

        User existingUser = new User();
        existingUser.setEmail("existinguser@example.com");
        existingUser.setPassword("Password1");

        when(userRepository.findByEmail(existingUser.getEmail())).thenReturn(existingUser);

        assertThrows(UserAlreadyExistException.class, () -> {
            userService.registerNewUserAccount(existingUser);
        });
    }

    @Test
    void testGetUserByID_UserFound() {

        Long userId = 1L;
        User expectedUser = new User();
        expectedUser.setId(userId);
        expectedUser.setLogin("TestUser");

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(expectedUser));

        Optional<User> resultUser = userService.getUserByID(userId);

        assertTrue(resultUser.isPresent());
        assertEquals(expectedUser, resultUser.get());
    }

    @Test
    void testGetUserByID_UserNotFound() {

        Long userId = 1L;

        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<User> resultUser = userService.getUserByID(userId);

        assertFalse(resultUser.isPresent());
    }

    @Test
    void testFindUserByEmail_UserFound() {

        String email = "user@example.com";
        User expectedUser = new User();
        expectedUser.setEmail(email);
        expectedUser.setLogin("TestUser");

        when(userRepository.findByEmail(email)).thenReturn(expectedUser);

        User resultUser = userService.findUserByEmail(email);

        assertNotNull(resultUser);
        assertEquals(expectedUser, resultUser);
    }

    @Test
    void testFindUserByEmail_UserNotFound() {

        String email = "nonexistent@example.com";

        when(userRepository.findByEmail(email)).thenReturn(null);

        User resultUser = userService.findUserByEmail(email);

        assertNull(resultUser);
    }

    @Test
    void testDeleteUser() {

        User userToDelete = new User();
        userToDelete.setId(1L);
        userToDelete.setLogin("TestUser");

        userService.deleteUser(userToDelete);


    }
}

