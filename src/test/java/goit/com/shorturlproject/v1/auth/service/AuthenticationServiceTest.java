package goit.com.shorturlproject.v1.auth.service;
import goit.com.shorturlproject.v1.auth.request.RegisterRequest;
import goit.com.shorturlproject.v1.auth.responce.AuthenticationResponse;
import goit.com.shorturlproject.v1.user.dto.User;
import goit.com.shorturlproject.v1.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AuthenticationServiceTest {

    @InjectMocks
    private AuthenticationService authenticationService;

    @Mock
    private UserService userService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegister() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("John");
        registerRequest.setLastName("Doe");
        registerRequest.setEmail("johndoe@example.com");
        registerRequest.setPassword("password123");
        registerRequest.setConfirmPassword("password123");
        registerRequest.setUserName("johndoe123");


        User existingUser = new User();
        existingUser.setUserName(registerRequest.getUserName());


        when(userService.findByUserName(registerRequest.getUserName()))
                .thenReturn(Optional.of(existingUser));

        AuthenticationResponse response = authenticationService.register(registerRequest);


        assertEquals("User with this username already exists", response.getError());
    }

    @Test
    public void testRegisterWhenNewUser() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("Alice");
        registerRequest.setLastName("Johnson");
        registerRequest.setEmail("alice@example.com");
        registerRequest.setPassword("alicePassword");
        registerRequest.setConfirmPassword("alicePassword");
        registerRequest.setUserName("alice123");


        User newUser = new User();
        newUser.setUserName(registerRequest.getUserName());


        when(userService.findByUserName(registerRequest.getUserName()))
                .thenReturn(Optional.empty());


        when(userService.registerNewUserAccount(Mockito.any(User.class)))
                .thenReturn(newUser);


        when(jwtService.generateToken(newUser))
                .thenReturn("test-token");

        AuthenticationResponse response = authenticationService.register(registerRequest);


        assertEquals("test-token", response.getToken());
    }
}
