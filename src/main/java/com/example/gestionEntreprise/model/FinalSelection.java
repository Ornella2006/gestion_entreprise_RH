package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
public class FinalSelection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFinalSelection;
    // ...autres champs à ajouter selon la table SQL...
}
