package com.example.gestionEntreprise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestionEntreprise.model.Language;

public interface LanguageRepository extends JpaRepository<Language, Long> {
}