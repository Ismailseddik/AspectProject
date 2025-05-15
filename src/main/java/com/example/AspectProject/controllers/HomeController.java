package com.example.AspectProject.controllers;

import com.example.AspectProject.services.QuranStreamingClient;
import com.example.AspectProject.services.QuranTextClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private QuranStreamingClient streamingClient;

    @Autowired
    private QuranTextClient textClient;

    @GetMapping("/home")
    public String showHomePage(@RequestParam(name = "surahId", required = false, defaultValue = "1") int surahId, Model model) {
        model.addAttribute("audioUrl", streamingClient.getAudioUrl(surahId, "Abdul_Basit_Murattal"));
        model.addAttribute("ayat", textClient.getFullSurah(surahId));
        model.addAttribute("surahId", surahId);
        model.addAttribute("surahs", textClient.getAllSurahNames()); // <-- for dropdown
        return "home";
    }
}