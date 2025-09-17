package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
public class EducationLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEducationLevel;

    @Column(nullable = false, length = 100)
    private String levelName;

    // Getters and setters
    // ...
}
