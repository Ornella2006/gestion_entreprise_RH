package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProfile;

    @Column(nullable = false, length = 100)
    private String nameProfile;

    // Getters and setters
    // ...
}
