package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
public class LanguageLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLanguageLevel;

    @ManyToOne
    @JoinColumn(name = "idLanguage")
    private Language language;

    @ManyToOne
    @JoinColumn(name = "idLevel")
    private Level level;

    // Getters and setters
    // ...
}
