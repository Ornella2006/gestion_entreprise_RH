package com.example.gestionEntreprise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestionEntreprise.model.Degree;

public interface DegreeRepository extends JpaRepository<Degree, Integer> 
{
}