package com.example.AspectProject.controllers;

import com.example.AspectProject.services.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SignupRestController {

    private final SignupService signupService;

    @Autowired
    public SignupRestController(SignupService signupService) {
        this.signupService = signupService;
    }

    @PostMapping("/signup")
    public boolean signup(@RequestParam String username, @RequestParam String password, @RequestParam String email) {
        return signupService.registerUser(username, password,email);
}
}