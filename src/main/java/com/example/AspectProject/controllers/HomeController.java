package com.example.AspectProject.controllers;

import com.example.AspectProject.services.QuranStreamingClient;
import com.example.AspectProject.services.QuranTextClient;
import com.example.AspectProject.services.QuranReciterClient;
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
    @Autowired
    private QuranReciterClient reciterClient;
    @GetMapping("/home")
    public String showHomePage(
            @RequestParam(name = "surahId", required = false, defaultValue = "1") int surahId,
            @RequestParam(name = "reciter", required = false, defaultValue = "Abdul_Basit_Murattal") String reciter,
            Model model) {

        model.addAttribute("audioUrl", streamingClient.getAudioUrl(surahId, reciter));
        model.addAttribute("downloadUrl", streamingClient.getAudioUrl(surahId, reciter));
        model.addAttribute("ayat", textClient.getFullSurah(surahId));
        model.addAttribute("surahId", surahId);
        model.addAttribute("surahs", textClient.getAllSurahNames());
        model.addAttribute("reciter", reciter); // keep selected
        model.addAttribute("reciters", reciterClient.getAllReciters()); // for dropdown

        return "home";
    }
}