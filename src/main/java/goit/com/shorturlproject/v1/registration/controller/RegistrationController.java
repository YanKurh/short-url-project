package goit.com.shorturlproject.v1.registration.controller;

import goit.com.shorturlproject.v1.registration.exception.UserAlreadyExistException;
import goit.com.shorturlproject.v1.user.dto.User;
import goit.com.shorturlproject.v1.user.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("v1")
public class RegistrationController {
    private final UserServiceImpl userServiceImpl;

    public RegistrationController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }
  
    @Operation(summary = "User registration")
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation error");
        }

        try {
            User registeredUser = userServiceImpl.registerNewUserAccount(user);
            return ResponseEntity.ok("User registered successfully");
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.badRequest().body("User with this email already exists");
        }
    }
}