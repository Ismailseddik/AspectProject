package com.example.AspectProject.dto;

import com.example.AspectProject.models.Favorite;

public class FavoriteViewDTO {
    private Long id;
    private int surahId;
    private int ayahNumber;
    private String ayahText;
    private String ayahTextArabic;
    private String surahName;
    private String surahNameArabic;

    // Constructor
    public FavoriteViewDTO(Favorite fav, String surahName, String surahNameArabic) {
        this.id = fav.getId();
        this.surahId = fav.getSurahId();
        this.ayahNumber = fav.getAyahNumber();
        this.ayahText = fav.getAyahText();
        this.ayahTextArabic = fav.getAyahTextArabic();
        this.surahName = surahName;
        this.surahNameArabic = surahNameArabic;
    }

    // Getters omitted for brevity

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSurahId() {
        return surahId;
    }

    public void setSurahId(int surahId) {
        this.surahId = surahId;
    }

    public String getAyahText() {
        return ayahText;
    }

    public void setAyahText(String ayahText) {
        this.ayahText = ayahText;
    }

    public int getAyahNumber() {
        return ayahNumber;
    }

    public void setAyahNumber(int ayahNumber) {
        this.ayahNumber = ayahNumber;
    }

    public String getSurahName() {
        return surahName;
    }

    public void setSurahName(String surahName) {
        this.surahName = surahName;
    }

    public String getSurahNameArabic() {
        return surahNameArabic;
    }

    public void setSurahNameArabic(String surahNameArabic) {
        this.surahNameArabic = surahNameArabic;
    }

    public String getAyahTextArabic() {
        return ayahTextArabic;
    }

    public void setAyahTextArabic(String ayahTextArabic) {
        this.ayahTextArabic = ayahTextArabic;
    }
}
