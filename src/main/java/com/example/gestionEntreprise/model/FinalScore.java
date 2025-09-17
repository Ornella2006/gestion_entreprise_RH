package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
public class FinalScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFinalScore;
    // ...autres champs à ajouter selon la table SQL...
}
