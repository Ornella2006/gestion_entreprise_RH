package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDepartment;

    @Column(nullable = false, length = 100)
    private String nameDepartment;

    // Getters and setters
    // ...
}
