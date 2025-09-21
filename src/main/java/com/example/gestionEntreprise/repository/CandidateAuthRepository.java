package com.example.gestionEntreprise.repository;

import com.example.gestionEntreprise.model.CandidateAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CandidateAuthRepository extends JpaRepository<CandidateAuth, Long> {
    Optional<CandidateAuth> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<CandidateAuth> findByCandidateIdCandidate(Long candidateId);
}