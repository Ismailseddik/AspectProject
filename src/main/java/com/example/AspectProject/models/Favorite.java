package com.example.AspectProject.models;
import jakarta.persistence.*;

@Entity
@Table(name="favorites")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String reciterName;
    private String verseReference;

    @Column(length = 1000)
    private String ayahText;

    public Favorite(){}

    public Favorite(Long id, Long userId, String reciterName, String verseReference, String ayahText) {
        this.id = id;
        this.userId = userId;
        this.reciterName = reciterName;
        this.verseReference = verseReference;
        this.ayahText = ayahText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getReciterName() {
        return reciterName;
    }

    public void setReciterName(String reciterName) {
        this.reciterName = reciterName;
    }

    public String getVerseReference() {
        return verseReference;
    }

    public void setVerseReference(String verseReference) {
        this.verseReference = verseReference;
    }

    public String getAyahText() {
        return ayahText;
    }

    public void setAyahText(String ayahText) {
        this.ayahText = ayahText;
    }
}
