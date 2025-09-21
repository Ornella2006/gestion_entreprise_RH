package com.example.gestionEntreprise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.gestionEntreprise.model.CandidateSkill;

public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, Long> {
    List<CandidateSkill> findByCandidateIdCandidate(Long candidateId);
    
    @Transactional
    @Modifying
    @Query("DELETE FROM CandidateSkill cs WHERE cs.candidate.idCandidate = :candidateId")
    void deleteByCandidateIdCandidate(@Param("candidateId") Long candidateId);
}