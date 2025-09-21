package com.example.gestionEntreprise.repository;

import com.example.gestionEntreprise.model.CandidateLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CandidateLanguageRepository extends JpaRepository<CandidateLanguage, Integer> {
    List<CandidateLanguage> findByCandidateIdCandidate(Integer candidateId);
    
    @Transactional
    @Modifying
    @Query("DELETE FROM CandidateLanguage cl WHERE cl.candidate.idCandidate = :candidateId")
    void deleteByCandidateIdCandidate(@Param("candidateId") Integer candidateId);
}