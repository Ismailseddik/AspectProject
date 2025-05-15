package com.example.AspectProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;

@Service
public class QuranTextClient {

    @Autowired
    private RestTemplate restTemplate;

    public List<String> getAyatForSurah(int surahId) {
        String url = "http://quran-service:8082/api/surahs/"+ surahId;
        Map<String, Object> surah = restTemplate.getForObject(url, Map.class);

        if (surah.containsKey("verses")) {
            return (List<String>) surah.get("verses");
        } else {
            // TEMP fallback for testing
            return List.of("Error: Verses not found in response.");}
    }
    public List<Map<String, Object>> getFullSurah(int surahId) {
        String url = "http://quran-service:8082/api/ayahs/surah/" + surahId;
        List<Map<String, Object>> response = restTemplate.getForObject(url, List.class);
        return response;
    }
    public List<Map<String, Object>> getAllSurahNames() {
        String url = "http://quran-service:8082/api/surahs";
        return restTemplate.getForObject(url, List.class);
    }
}
