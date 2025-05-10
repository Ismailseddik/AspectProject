package com.example.AspectProject.controllers;

import com.example.AspectProject.models.Favorite;
import com.example.AspectProject.services.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FavoritesPageController {

    private FavoritesService favoritesService;

    public FavoritesPageController(FavoritesService favoritesService) {
        this.favoritesService = favoritesService;
    }

    @GetMapping("/favorites")
    public String getFavoritesPage(Model model) {
        List<Favorite> favorites = favoritesService.getUserFavorites();
        model.addAttribute("favorites", favorites != null ? favorites : new ArrayList<>());
        return "favorites";
    }
}