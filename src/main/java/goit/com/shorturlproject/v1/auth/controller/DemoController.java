package goit.com.shorturlproject.v1.auth.controller;

import goit.com.shorturlproject.v1.auth.service.JwtService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo-controller")
@Hidden
public class DemoController {
    private final JwtService jwtService;

    public DemoController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping
    public ResponseEntity<String> securedResource() {

        return ResponseEntity.ok("Secured resource accessed");
    }
}
