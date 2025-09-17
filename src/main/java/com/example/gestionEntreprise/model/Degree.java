package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
public class Degree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDegree;

    @Column(nullable = false, length = 100)
    private String degreeName;

    // Getters and setters
    // ...
}
