package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCity;

    @Column(nullable = false, length = 100)
    private String nameCity;

    // Getters and setters
    // ...
}
