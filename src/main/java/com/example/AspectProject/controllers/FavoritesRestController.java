package com.example.AspectProject.controllers;

import com.example.AspectProject.models.Favorite;
import com.example.AspectProject.services.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoritesRestController {

    @Autowired
    private final FavoritesService favoritesService;

    public FavoritesRestController(FavoritesService favoritesService) {
        this.favoritesService = favoritesService;
    }

    @GetMapping
    public List<Favorite> getFavorites() {
        return favoritesService.getUserFavorites();
    }

    @PostMapping
    public Favorite addFavorite(@RequestBody Favorite favorite) {
        return favoritesService.addFavorite(favorite);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFavorite(@PathVariable("id") Long id) {
        favoritesService.deleteFavoriteById(id);
        return ResponseEntity.noContent().build();
    }
}
