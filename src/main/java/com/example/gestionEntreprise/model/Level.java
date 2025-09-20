package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
@Table(name = "level")
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlevel")
    private Integer idLevel;

    @Column(name = "level_name", nullable = false, length = 100)
    private String levelName;

    @Column(name = "score_level")
    private Integer scoreLevel;

    // Constructeurs
    public Level() {
    }

    public Level(String levelName, Integer scoreLevel) {
        this.levelName = levelName;
        this.scoreLevel = scoreLevel;
    }

    // Getters et Setters
    public Integer getIdLevel() {
        return idLevel;
    }

    public void setIdLevel(Integer idLevel) {
        this.idLevel = idLevel;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getScoreLevel() {
        return scoreLevel;
    }

    public void setScoreLevel(Integer scoreLevel) {
        this.scoreLevel = scoreLevel;
    }

    // Méthode toString()
    @Override
    public String toString() {
        return "Level{" +
                "idLevel=" + idLevel +
                ", levelName='" + levelName + '\'' +
                ", scoreLevel=" + scoreLevel +
                '}';
    }
}