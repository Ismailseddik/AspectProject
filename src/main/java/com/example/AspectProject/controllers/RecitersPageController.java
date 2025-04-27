package com.example.AspectProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecitersPageController {

    @GetMapping("/reciters")
    public String recitersPage() {
        return "reciters"; // returns reciters.html from templates
}
}