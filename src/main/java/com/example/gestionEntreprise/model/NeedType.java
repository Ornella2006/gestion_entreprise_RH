package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
public class NeedType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNeedType;

    @Column(nullable = false, length = 100)
    private String nameNeed;

    // Getters and setters
    // ...
}
