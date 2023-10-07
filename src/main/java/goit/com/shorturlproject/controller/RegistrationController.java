package goit.com.shorturlproject.controller;
import goit.com.shorturlproject.entity.request.SignupRequest;
import goit.com.shorturlproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "registration-form"; // Это имя представления (HTML формы регистрации)
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@ModelAttribute SignupRequest signupRequest) {
        return userService.saveUser(signupRequest);
    }

    @GetMapping("/confirm-account")
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String confirmationToken) {
        return userService.confirmEmail(confirmationToken);
    }
}