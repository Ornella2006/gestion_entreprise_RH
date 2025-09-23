package com.example.gestionEntreprise.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import org.springframework.transaction.annotation.Transactional;

import com.example.gestionEntreprise.model.Candidate;
import com.example.gestionEntreprise.model.CandidateSkill;

public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, Integer> {
    List<CandidateSkill> findByCandidateIdCandidate(Integer candidateId);
    
    @Transactional
    @Modifying
    @Query("DELETE FROM CandidateSkill cs WHERE cs.candidate.idCandidate = :candidateId")
    void deleteByCandidateIdCandidate(@Param("candidateId") Integer candidateId);

    @Query("SELECT cs.candidate FROM CandidateSkill cs WHERE LOWER(cs.skill.skillName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Candidate> findCandidatesBySkillNameContaining(@Param("name") String name);
}