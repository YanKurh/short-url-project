package goit.com.shorturlproject.controller;
import goit.com.shorturlproject.entity.User;
import goit.com.shorturlproject.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserServiceImpl userService;


    @GetMapping("/user/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration-form";
    }


    }
   // @GetMapping("/confirm-account")
    //public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String confirmationToken) {
       // return userService.confirmEmail(confirmationToken);
    //}
