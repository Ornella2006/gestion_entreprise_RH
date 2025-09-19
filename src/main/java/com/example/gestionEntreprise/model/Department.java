package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDepartment;

    @Column(nullable = false, length = 100,name = "name_department")
    private String nameDepartment;

    // Getters and setters
    // ...
}
