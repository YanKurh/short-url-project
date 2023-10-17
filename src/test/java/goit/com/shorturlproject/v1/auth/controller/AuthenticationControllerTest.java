package goit.com.shorturlproject.v1.auth.controller;

import goit.com.shorturlproject.v1.auth.request.AuthenticationRequest;
import goit.com.shorturlproject.v1.auth.request.RegisterRequest;
import goit.com.shorturlproject.v1.auth.responce.AuthenticationResponse;
import goit.com.shorturlproject.v1.auth.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AuthenticationControllerTest {

    private AuthenticationController authenticationController;
    private AuthenticationService authenticationService;

    @BeforeEach
    public void setup() {
        authenticationService = mock(AuthenticationService.class);
        authenticationController = new AuthenticationController(authenticationService);
    }

    @Test
    public void testRegister() {

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("John");
        registerRequest.setLastName("Doe");
        registerRequest.setEmail("johndoe@example.com");
        registerRequest.setPassword("password123");
        registerRequest.setConfirmPassword("password123");
        registerRequest.setUserName("johndoe");


        AuthenticationResponse expectedResponse = AuthenticationResponse.builder()
                .Token("Token")
                .error(null)
                .build();;


        when(authenticationService.register(registerRequest)).thenReturn(expectedResponse);

        ResponseEntity<AuthenticationResponse> responseEntity = authenticationController.register(registerRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    public void testAuthenticate() {

        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setUserName("johndoe");
        authenticationRequest.setPassword("password123");


        AuthenticationResponse expectedResponse = AuthenticationResponse.builder()
                .Token("token")
                .error(null)
                .build();


        when(authenticationService.authenticate(authenticationRequest)).thenReturn(expectedResponse);


        ResponseEntity<AuthenticationResponse> responseEntity = authenticationController.authenticate(authenticationRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }
}
