package com.example.AspectProject.services;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

@Service
public class LoginService {

    private final RestTemplate restTemplate;
    public String loggedInUsername;
    @Autowired
    public LoginService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean validateLogin(String username, String password, HttpServletResponse servletResponse) {
        String url = "http://user-service:9090/auth/login";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = "username=" + username + "&password=" + password;
        HttpEntity<String> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                // Forward the JWT cookie to the browser
                String cookie = response.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
                if (cookie != null) {
                    servletResponse.setHeader(HttpHeaders.SET_COOKIE, cookie);
                }
                loggedInUsername = username;
                return true;
            }
        } catch (Exception e) {
            System.err.println("Login failed: " + e.getMessage());
        }

        return false;
    }
}