package com.example.gestionEntreprise.model;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class MaritalStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMaritalStatus;
    private String status;

    // Getters and setters
    public Long getIdMaritalStatus() { return idMaritalStatus; }
    public void setIdMaritalStatus(Long idMaritalStatus) { this.idMaritalStatus = idMaritalStatus; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}