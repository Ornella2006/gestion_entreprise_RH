package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
public class Assessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAssessment;
    // ... autres champs et relations ...
}