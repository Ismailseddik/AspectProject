package com.example.AspectProject.controllers;

import com.example.AspectProject.models.Favorite;
import com.example.AspectProject.services.FavoritesService;
import com.example.AspectProject.services.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class FavoritesPageController {

    private final FavoritesService favoritesService;
    private final LoginService loginService;

    public FavoritesPageController(FavoritesService favoritesService, LoginService loginService) {
        this.favoritesService = favoritesService;
        this.loginService = loginService;
    }

    @GetMapping("/favorites")
    public String getFavoritesPage(Model model) {
        String username = loginService.loggedInUsername; // ✅ directly using the field
        if (username == null) {
            return "redirect:/login";
        }

        List<Favorite> favorites = favoritesService.getFavoritesByUsername(username);
        model.addAttribute("favorites", favorites != null ? favorites : new ArrayList<>());
        return "favorites";
    }
}