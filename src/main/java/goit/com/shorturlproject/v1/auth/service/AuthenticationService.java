package goit.com.shorturlproject.v1.auth.service;
import goit.com.shorturlproject.v1.auth.request.RegisterRequest;
import goit.com.shorturlproject.v1.auth.request.AuthenticationRequest;
import goit.com.shorturlproject.v1.auth.responce.AuthenticationResponse;
import goit.com.shorturlproject.v1.registration.exception.UserAlreadyExistException;
import goit.com.shorturlproject.v1.user.dto.User;
import goit.com.shorturlproject.v1.user.exceptions.UserNotFoundException;
import goit.com.shorturlproject.v1.user.repository.UserRepository;
import goit.com.shorturlproject.v1.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userServiceImpl;


    public AuthenticationResponse register(RegisterRequest registerRequest) {
        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        user.setConfirmPassword(registerRequest.getConfirmPassword());
        user.setUserName(registerRequest.getUserName());

        Optional<User> existingUser = userServiceImpl.findByUserName(user.getUsername());
        if (existingUser.isPresent()) {
            AuthenticationResponse response = new AuthenticationResponse();
            response.setError("User with this username already exists");
            return response;
        }

        try {
            User registeredUser = userServiceImpl.registerNewUserAccount(user);
            var jwtToken = jwtService.generateToken(registeredUser);
            return AuthenticationResponse.builder()
                    .Token(jwtToken)
                    .build();
        } catch (UserAlreadyExistException e) {
            AuthenticationResponse response = new AuthenticationResponse();
            response.setError("User with this email already exists");
            return response;
        }
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserName(),
                        request.getPassword()
                )
        );
        var user = repository.findByUserName(request.getUserName())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .Token(jwtToken)
                .build();
    }
}