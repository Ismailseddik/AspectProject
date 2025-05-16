package com.example.AspectProject.services;

import com.example.AspectProject.models.Favorite;
import com.example.AspectProject.repositories.FavoriteRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FavoritesService {

    private final FavoriteRepository favoriteRepository;
    private final HttpSession session;

    @Autowired
    public FavoritesService(FavoriteRepository favoriteRepository, HttpSession session) {
        this.favoriteRepository = favoriteRepository;
        this.session = session;
    }

    public List<Favorite> getUserFavorites(String username) {
        return favoriteRepository.findByUsername(username);
    }

    public Favorite addFavorite(String username, Favorite favorite) {
        if (username == null || username.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is required");
        }

        favorite.setUsername(username); // Set the correct user for this favorite
        return favoriteRepository.save(favorite);
    }
    @Transactional
    public void deleteFavoriteById(Long id) {
        if (!favoriteRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Favorite not found");
        }
        favoriteRepository.deleteById(id);
    }
    public List<Favorite> getFavoritesByUsername(String username) {
        return favoriteRepository.findByUsername(username);
    }
}