package com.example.AspectProject.controllers;

import com.example.AspectProject.services.LoginService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api")
public class LoginRestController {

    private final LoginService loginService;

    @Autowired
    public LoginRestController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public boolean login(@RequestParam String username,
                         @RequestParam String password,
                         HttpServletResponse response) {
        return loginService.validateLogin(username, password, response);
    }
}