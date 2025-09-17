package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idActor;

    @Column(nullable = false, length = 100)
    private String roleName;

    @Column(columnDefinition = "TEXT")
    private String description;

    // Getters and setters
    public Long getIdActor() { return idActor; }
    public void setIdActor(Long idActor) { this.idActor = idActor; }
    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
