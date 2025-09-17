package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
public class ContractType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContractType;

    @Column(nullable = false, length = 100)
    private String nameContract;

    // Getters and setters
    // ...
}
