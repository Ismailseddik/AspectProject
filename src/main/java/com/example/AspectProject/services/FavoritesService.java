package com.example.AspectProject.services;

import com.example.AspectProject.models.Favorite;
import com.example.AspectProject.repositories.FavoriteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FavoritesService {

    @Autowired
    private final FavoriteRepository favoriteRepository;

    public FavoritesService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    public List<Favorite> getUserFavorites(){
        return favoriteRepository.findAll();
    }

    public Favorite addFavorite(Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    @Transactional
    public void deleteFavoriteById(Long id) {
        if (!favoriteRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Favorite not found");
        }
        favoriteRepository.deleteById(id);
    }
}