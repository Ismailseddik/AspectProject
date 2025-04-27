package com.example.AspectProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("username", "Ismail");
        model.addAttribute("favoriteSurah", "Al-Fatiha");
        return "home";
}
}