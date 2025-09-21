package com.example.gestionEntreprise.repository;

import com.example.gestionEntreprise.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience, Integer> {
    List<Experience> findByCandidateIdCandidate(Integer candidateId);
    
    @Transactional
    @Modifying
    @Query("DELETE FROM Experience e WHERE e.candidate.idCandidate = :candidateId")
    void deleteByCandidateIdCandidate(@Param("candidateId") Integer candidateId);
}