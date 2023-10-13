package goit.com.shorturlproject.v1.auth.controllers;

import goit.com.shorturlproject.v1.auth.TockenGenerator.JwtGeneratorInterface;

import goit.com.shorturlproject.v1.user.dto.User;
import goit.com.shorturlproject.v1.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private UserService userService;
    private JwtGeneratorInterface jwtGenerator;

    @Autowired
    public UserController(UserService userService, JwtGeneratorInterface jwtGenerator){
        this.userService=userService;
        this.jwtGenerator=jwtGenerator;
    }
    @PostMapping("/register")
    public ResponseEntity<?> postUser(@RequestBody User user){
        try{
            userService.save(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
   /* @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            if(user.getEmail() == null || user.getPassword() == null) {
                throw new UserNotFoundException("UserName or Password is Empty");
            }
            User userData = userService.getUserByNameAndPassword(user.getEmail(), user.getPassword());
            if(userData == null){
                throw new UserNotFoundException("UserName or Password is Invalid");
            }
            return new ResponseEntity<>(jwtGenerator.generateToken(user), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }*/
}
