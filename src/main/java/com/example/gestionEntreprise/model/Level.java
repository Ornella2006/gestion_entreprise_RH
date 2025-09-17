package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLevel;

    @Column(nullable = false, length = 100)
    private String levelName;

    private Integer scoreLevel;

    // Getters and setters
    // ...
}
