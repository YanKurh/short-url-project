package goit.com.shorturlproject.v1.registration;

import goit.com.shorturlproject.v1.ITestContainer;
import goit.com.shorturlproject.v1.registration.controller.RegistrationController;
import goit.com.shorturlproject.v1.registration.exception.UserAlreadyExistException;
import goit.com.shorturlproject.v1.user.dto.User;
import goit.com.shorturlproject.v1.user.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import static org.junit.Assert.assertEquals;

public class RegistrationControllerTest implements ITestContainer {

    private RegistrationController registrationController;

    @Mock
    private UserServiceImpl userServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        registrationController = new RegistrationController(userServiceImpl);
    }

    @Test
    public void testRegisterUser_InvalidUser() {
        User user = new User();

        user.setEmail("invalidemail");
        user.setPassword("123");

        BindingResult bindingResult = new BeanPropertyBindingResult(user, "user");

        ResponseEntity<?> response = registrationController.registerUser(user, bindingResult);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Validation error", response.getBody());
    }

    @Test
    public void testRegisterUser_ValidUser() {
        User user = new User();

        user.setEmail("valid_email@example.com");
        user.setPassword("ValidPassword123");

        BindingResult bindingResult = new BeanPropertyBindingResult(user, "user");

        Mockito.when(userServiceImpl.registerNewUserAccount(Mockito.any(User.class)))
                .thenReturn(new User());

        ResponseEntity<?> response = registrationController.registerUser(user, bindingResult);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("User registered successfully", response.getBody());
    }


    @Test
    public void testRegisterUser_UserAlreadyExists() {
        User user = new User();

        BindingResult bindingResult = new BeanPropertyBindingResult(user, "user");

        Mockito.when(userServiceImpl.registerNewUserAccount(Mockito.any(User.class)))
                .thenThrow(new UserAlreadyExistException("User with this email already exists"));

        ResponseEntity<?> response = registrationController.registerUser(user, bindingResult);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("User with this email already exists", response.getBody());
    }
}