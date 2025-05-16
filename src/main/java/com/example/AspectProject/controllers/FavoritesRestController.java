package com.example.AspectProject.controllers;

import com.example.AspectProject.models.Favorite;
import com.example.AspectProject.services.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoritesRestController {

    private final FavoritesService favoritesService;

    @Autowired
    public FavoritesRestController(FavoritesService favoritesService) {
        this.favoritesService = favoritesService;
    }

    @PostMapping
    public Favorite addFavorite(@RequestBody Favorite favorite) {
        return favoritesService.addFavorite(favorite.getUsername(), favorite);
    }

    @GetMapping
    public List<Favorite> getFavorites(@RequestParam String username) {
        return favoritesService.getUserFavorites(username);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFavorite(@PathVariable("id") Long id) {
        favoritesService.deleteFavoriteById(id);
        return ResponseEntity.noContent().build();
    }
}