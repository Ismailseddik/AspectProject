package com.example.AspectProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FavoritesPageController {

    @GetMapping("/favorites")
    public String favoritesPage() {
        return "favorites"; // maps to favorites.html
}
}