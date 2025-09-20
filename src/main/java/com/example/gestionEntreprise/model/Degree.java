package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
public class Degree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDegree;

    @Column(nullable = false, length = 100,name = "degree_name")
    private String degreeName;

    // Getters and setters
    // ...
}
