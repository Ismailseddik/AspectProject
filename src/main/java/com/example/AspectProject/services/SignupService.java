package com.example.AspectProject.services;

import org.springframework.stereotype.Service;

@Service
public class SignupService {

    public boolean registerUser(String username, String password, String email) {
        // For now, just simulate successful registration
        System.out.println("Registering user: " + username + " with email: " + email);
        return true;
}
}