package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
@Table(name = "job_position")
public class JobPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idjob_position")
    private Integer idPosition;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    // Constructeurs
    public JobPosition() {
    }

    public JobPosition(String title) {
        this.title = title;
    }

    // Getters et Setters
    public Integer getId() {
        return idPosition;
    }

    public void setId(Integer idPosition) {
        this.idPosition = idPosition;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Méthode toString()
    @Override
    public String toString() {
        return "JobPosition{" +
                "idPosition=" + idPosition +
                ", title='" + title + '\'' +
                '}';
    }
}