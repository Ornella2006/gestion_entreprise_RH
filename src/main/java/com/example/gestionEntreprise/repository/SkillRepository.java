package com.example.gestionEntreprise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestionEntreprise.model.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}