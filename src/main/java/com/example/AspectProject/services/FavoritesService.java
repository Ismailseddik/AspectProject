package com.example.AspectProject.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Arrays;

@Service
public class FavoritesService {

    public List<String> getUserFavorites() {
        // Placeholder favorites
        return Arrays.asList(
                "Al-Fatiha - Ayah 1: In the name of Allah, the Most Gracious, the Most Merciful.",
                "Al-Baqara - Ayah 255: Ayat al-Kursi..."
                );
}
}