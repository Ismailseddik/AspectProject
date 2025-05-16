package com.example.AspectProject.models;

import jakarta.persistence.*;

@Entity
@Table(name = "favorites")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private int surahId;

    @Column(nullable = false)
    private int ayahNumber;

    @Column(length = 1000)
    private String ayahText;

    @Column(length = 1000)
    private String ayahTextArabic;

    @Column(nullable = false)
    private String surahName;

    @Column
    private String surahNameArabic;

    public Favorite() {}

    public Favorite(String username, int surahId, int ayahNumber, String ayahText, String ayahTextArabic,
                    String surahName, String surahNameArabic) {
        this.username = username;
        this.surahId = surahId;
        this.ayahNumber = ayahNumber;
        this.ayahText = ayahText;
        this.ayahTextArabic = ayahTextArabic;
        this.surahName = surahName;
        this.surahNameArabic = surahNameArabic;
    }

    public Long getId() { return id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public int getSurahId() { return surahId; }

    public void setSurahId(int surahId) { this.surahId = surahId; }

    public int getAyahNumber() { return ayahNumber; }

    public void setAyahNumber(int ayahNumber) { this.ayahNumber = ayahNumber; }

    public String getAyahText() { return ayahText; }

    public void setAyahText(String ayahText) { this.ayahText = ayahText; }

    public String getAyahTextArabic() { return ayahTextArabic; }

    public void setAyahTextArabic(String ayahTextArabic) { this.ayahTextArabic = ayahTextArabic; }

    public String getSurahName() { return surahName; }

    public void setSurahName(String surahName) { this.surahName = surahName; }

    public String getSurahNameArabic() { return surahNameArabic; }

    public void setSurahNameArabic(String surahNameArabic) { this.surahNameArabic = surahNameArabic; }
}