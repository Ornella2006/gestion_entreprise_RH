package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
@Table(name = "degree")
public class Degree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddegree")
    private Integer idDegree; // Changé de Long à Integer

    @Column(name = "degree_name", nullable = false, length = 100)
    private String degreeName;

    // Getters and setters
    public Integer getIdDegree() { return idDegree; }
    public void setIdDegree(Integer idDegree) { this.idDegree = idDegree; }
    public String getDegreeName() { return degreeName; }
    public void setDegreeName(String degreeName) { this.degreeName = degreeName; }
}