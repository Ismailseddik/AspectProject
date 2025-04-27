package com.example.AspectProject.services;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public boolean validateLogin(String username, String password) {
        // For now, just accept anything (we'll add real logic later)
        return true;
}
}