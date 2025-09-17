package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLanguage;

    @Column(nullable = false, length = 100)
    private String languageName;

    // Getters and setters
    // ...
}
