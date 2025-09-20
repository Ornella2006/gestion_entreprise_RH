package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
@Table(name = "status")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idstatus")
    private Integer idStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "name_status", nullable = false)
    private StatusType nameStatus;

    // Constructeurs
    public Status() {
    }

    public Status(StatusType nameStatus) {
        this.nameStatus = nameStatus;
    }

    // Getters et Setters
    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

    public StatusType getNameStatus() {
        return nameStatus;
    }

    public void setNameStatus(StatusType nameStatus) {
        this.nameStatus = nameStatus;
    }

    // Méthode toString()
    @Override
    public String toString() {
        return "Status{" +
                "idStatus=" + idStatus +
                ", nameStatus=" + nameStatus +
                '}';
    }
}