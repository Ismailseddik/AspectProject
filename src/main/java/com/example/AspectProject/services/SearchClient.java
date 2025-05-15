package com.example.AspectProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class SearchClient {

    @Autowired
    private RestTemplate restTemplate;

    public List<Map<String, Object>> search(String keyword) {
        List<Map<String, Object>> combinedResults = new ArrayList<>();

        // Surah search
        Map<String, Object> surahResponse = restTemplate.getForObject(
                "http://quran-service:8082/api/surahs/search?keyword=" + keyword, Map.class);
        List<Map<String, Object>> surahResults = (List<Map<String, Object>>) surahResponse.get("results");

        // Ayah search
        Map<String, Object> ayahResponse = restTemplate.getForObject(
                "http://quran-service:8082/api/ayahs/search?keyword=" + keyword, Map.class);
        List<Map<String, Object>> ayahResults = (List<Map<String, Object>>) ayahResponse.get("results");

        if (surahResults != null) {
            surahResults.forEach(r -> r.put("type", "surah"));
            combinedResults.addAll(surahResults);
        }

        if (ayahResults != null) {
            ayahResults.forEach(r -> r.put("type", "ayah"));
            combinedResults.addAll(ayahResults);
        }

        return combinedResults;
    }
}