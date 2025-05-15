package com.example.AspectProject.controllers;

import com.example.AspectProject.services.SearchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SearchRestController {

    private final SearchService searchService;

    public SearchRestController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public List<Map<String, Object>> search(@RequestParam String q) {
        return searchService.searchQuran(q);
    }

    @GetMapping("/surahs/search")
    public Map<String, Object> searchSurahs(@RequestParam String keyword) {
        List<Map<String, Object>> results = searchService.searchQuran(keyword).stream()
                .filter(r -> "surah".equals(r.get("type")))
                .toList();
        return Map.of("results", results, "totalResults", results.size());
    }

    @GetMapping("/ayahs/search")
    public Map<String, Object> searchAyahs(@RequestParam String keyword) {
        List<Map<String, Object>> results = searchService.searchQuran(keyword).stream()
                .filter(r -> "ayah".equals(r.get("type")))
                .toList();
        return Map.of("results", results, "totalResults", results.size());
    }
}