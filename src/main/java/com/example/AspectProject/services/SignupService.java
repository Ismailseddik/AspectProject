package com.example.AspectProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SignupService {

    private final RestTemplate restTemplate;

    @Autowired
    public SignupService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean registerUser(String username, String password, String email) {
        String url = "http://user-service:9090/auth/signup";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = "username=" + username + "&password=" + password; // email unused in user-service, optional
        HttpEntity<String> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            return response.getStatusCode() == HttpStatus.OK;
        } catch (Exception e) {
            System.err.println("Signup failed: " + e.getMessage());
            return false;
        }
    }
}