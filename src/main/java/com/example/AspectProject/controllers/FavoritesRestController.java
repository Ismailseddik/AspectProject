package com.example.AspectProject.controllers;

import com.example.AspectProject.services.FavoritesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FavoritesRestController {

    private final FavoritesService favoritesService;

    public FavoritesRestController(FavoritesService favoritesService) {
        this.favoritesService = favoritesService;
    }

    @GetMapping("/favorites")
    public List<String> getFavorites() {
        return favoritesService.getUserFavorites();
}
}