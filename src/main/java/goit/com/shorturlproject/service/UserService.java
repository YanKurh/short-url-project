package goit.com.shorturlproject.service;

import goit.com.shorturlproject.entity.request.SignupRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> saveUser(SignupRequest signupRequest);         //(User user);

    ResponseEntity<?> confirmEmail(String confirmationToken);
}