package com.example.gestionEntreprise.repository;

import org.springframework.stereotype.Repository;
import com.example.gestionEntreprise.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}