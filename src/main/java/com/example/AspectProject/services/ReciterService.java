package com.example.AspectProject.services;

import com.example.AspectProject.models.Reciter;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ReciterService {

    public List<Reciter> getReciters() {
        return Arrays.asList(
                new Reciter("Abdul Basit", "/img/reciters/abdulbasit.png"),
                new Reciter("Mishary Alafasy", "/img/reciters/alafasy.png"),
                new Reciter("Saad Al-Ghamdi", "/img/reciters/ghamdi.png"),
                new Reciter("Maher Al-Muaiqly", "/img/reciters/maher.png"),
                new Reciter("Abdullah Matrood", "/img/reciters/matrood.png")
                );
}
}