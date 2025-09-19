package com.example.gestionEntreprise.repository;

import com.example.gestionEntreprise.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {}
