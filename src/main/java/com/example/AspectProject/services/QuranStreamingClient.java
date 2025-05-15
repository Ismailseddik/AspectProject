package com.example.AspectProject.services;

import org.springframework.stereotype.Service;

@Service
public class QuranStreamingClient {
    public String getAudioUrl(int surahId, String reciter) {
        return "http://localhost:8083/api/stream/play?surahId=" + surahId + "&reciter=" + reciter;
    }
}