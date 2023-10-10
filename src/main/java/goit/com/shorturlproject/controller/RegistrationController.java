package goit.com.shorturlproject.controller;
import goit.com.shorturlproject.dto.UserDto;
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
    public String showRegistrationForm(final HttpServletRequest request, final Model model) {
        LOGGER.debug("Rendering registration page.");
        final UserDto accountDto = new UserDto();
        model.addAttribute("user", accountDto);
        return "registration-form";
    }

    @PostMapping("/user/registration")
    public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid final UserDto userDto, final HttpServletRequest request, final Errors errors) {
        LOGGER.debug("Registering user account with information: {}", userDto);

        try {
            final User registered = userService.registerNewUserAccount(userDto); } catch (final RuntimeException ex) {
            LOGGER.warn("Unable to register user", ex);
            return new ModelAndView("emailError", "user", userDto);
        }
        return new ModelAndView("successRegister", "user", userDto);
    }

    }
   // @GetMapping("/confirm-account")
    //public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String confirmationToken) {
       // return userService.confirmEmail(confirmationToken);
    //}
