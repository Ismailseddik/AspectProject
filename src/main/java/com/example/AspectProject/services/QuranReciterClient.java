package com.example.AspectProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class QuranReciterClient {

    private final RestTemplate restTemplate;

    @Autowired
    public QuranReciterClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<String> getAllReciters() {
        ResponseEntity<List<String>> response = restTemplate.exchange(
                "http://quran-streaming-service:8080/api/stream/reciters",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        return response.getBody();
    }
}