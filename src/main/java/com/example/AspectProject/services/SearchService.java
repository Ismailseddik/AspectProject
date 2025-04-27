package com.example.AspectProject.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Arrays;

@Service
public class SearchService {

    public List<String> searchQuran(String query) {
        // Temporary hardcoded response
        return Arrays.asList(
                "Result for '" + query + "' found in Surah Al-Baqara",
                "Result for '" + query + "' found in Surah Al-Fatiha"
                );
}
}