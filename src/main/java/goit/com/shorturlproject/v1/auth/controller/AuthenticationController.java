package goit.com.shorturlproject.v1.auth.controller;

import goit.com.shorturlproject.v1.auth.dto.UserRequest;
import goit.com.shorturlproject.v1.auth.request.AuthenticationRequest;
import goit.com.shorturlproject.v1.auth.responce.AuthenticationResponse;
import goit.com.shorturlproject.v1.auth.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid
            @RequestBody UserRequest registerRequest
    ) {
        return ResponseEntity.ok(service.register(registerRequest));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
