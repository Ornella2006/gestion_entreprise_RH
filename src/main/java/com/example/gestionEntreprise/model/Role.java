package com.example.gestionEntreprise.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRole;

    private String roleName;
    private String description;

    public Role() {}
    // Getters and setters
    public Long getIdRole() {return this.idRole;}
    public void setIdRole(Long idRole) {this.idRole = idRole;}
    public String getRoleName() {return this.roleName;}
    public void setRoleName(String roleName) {this.roleName = roleName;}
    public String getDescription() {return this.description;}
    public void setDescription(String description) {this.description = description;}
}
