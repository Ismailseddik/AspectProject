package com.example.AspectProject.controllers;

import com.example.AspectProject.services.SearchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SearchRestController {

    private final SearchService searchService;

    public SearchRestController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public List<String> search(@RequestParam String q) {
        return searchService.searchQuran(q);
}
}
