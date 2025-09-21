package com.example.gestionEntreprise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestionEntreprise.model.Level;

public interface LevelRepository extends JpaRepository<Level, Long> {
}