package com.example.AspectProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SearchService {

    @Autowired
    private SearchClient searchClient;

    public List<Map<String, Object>> searchQuran(String query) {
        return searchClient.search(query);
    }
}