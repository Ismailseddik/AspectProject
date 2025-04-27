package com.example.AspectProject.controllers;

import com.example.AspectProject.services.ReciterService;
import com.example.AspectProject.models.Reciter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RecitersRestController {

    private final ReciterService reciterService;

    public RecitersRestController(ReciterService reciterService) {
        this.reciterService = reciterService;
    }

    @GetMapping("/reciters")
    public List<Reciter> getReciters() {
        return reciterService.getReciters();
}
}