package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
@Table(name = "skill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idskill")
    private Integer idSkill; // Changé de Long à Integer

    @Column(name = "skill_name", nullable = false, length = 100)
    private String skillName;

    // Getters and setters
    public Integer getIdSkill() { return idSkill; }
    public void setIdSkill(Integer idSkill) { this.idSkill = idSkill; }
    public String getSkillName() { return skillName; }
    public void setSkillName(String skillName) { this.skillName = skillName; }


    // Méthode toString()
    @Override
    public String toString() {
        return "Skill{" +
                "idSkill=" + idSkill +
                ", skillName='" + skillName + '\'' +
                '}';
    }
}