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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class RegistrationControllerTest implements ITestContainer {

    private RegistrationController registrationController;

    @Mock
    private UserServiceImpl userServiceImpl;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        registrationController = new RegistrationController(userServiceImpl);
    }

    @Test
    void testRegistrationController_Success() {
        when(bindingResult.hasErrors()).thenReturn(false);

        when(userServiceImpl.registerNewUserAccount(Mockito.any(User.class))).thenReturn(new User());

        ResponseEntity<?> response = registrationController.registerUser(new User(), bindingResult);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User registered successfully", response.getBody());
    }

    @Test
    void testRegistrationController_ValidationFailure() {
        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<?> response = registrationController.registerUser(new User(), bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Validation error", response.getBody());
    }


    @Test
    public void testRegisterUser_UserAlreadyExists() {
        User user = new User();

        BindingResult bindingResult = new BeanPropertyBindingResult(user, "user");

        when(userServiceImpl.registerNewUserAccount(Mockito.any(User.class)))
                .thenThrow(new UserAlreadyExistException("User with this email already exists"));

        ResponseEntity<?> response = registrationController.registerUser(user, bindingResult);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("User with this email already exists", response.getBody());
    }
}