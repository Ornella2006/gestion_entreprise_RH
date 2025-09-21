package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
@Table(name = "language")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlanguage")
    private Integer idLanguage; // Changé de Long à Integer

    @Column(name = "language_name", nullable = false, length = 100)
    private String languageName;

    // Getters and setters
    public Integer getIdLanguage() { return idLanguage; }
    public void setIdLanguage(Integer idLanguage) { this.idLanguage = idLanguage; }
    public String getLanguageName() { return languageName; }
    public void setLanguageName(String languageName) { this.languageName = languageName; }


    // Méthode toString()
    @Override
    public String toString() {
        return "Language{" +
                "idLanguage=" + idLanguage +
                ", languageName='" + languageName + '\'' +
                '}';
    }
}