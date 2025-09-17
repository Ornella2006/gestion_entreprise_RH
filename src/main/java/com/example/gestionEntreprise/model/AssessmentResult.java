package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
public class AssessmentResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAssessmentResult;
    // ...autres champs à ajouter selon la table SQL...
}
