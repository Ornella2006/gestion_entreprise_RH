package com.example.gestionEntreprise.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.gestionEntreprise.model.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
    @Query("SELECT c FROM Candidate c JOIN c.person p WHERE p.email = :email")
    Candidate findByPersonEmail(@Param("email") String email);

    @Query("SELECT c FROM Candidate c WHERE c.person.idPerson = :personId")
    Candidate findByPersonId(@Param("personId") Integer personId);

    @Query(value = "SELECT * FROM get_matching_candidates_dynamic(:idRequest)", nativeQuery = true)
    List<MatchingCandidateProjection> findMatchingCandidates(@Param("idRequest") Integer idRequest);

    @Query("SELECT c FROM Candidate c WHERE c.educationLevel.idEducationLevel >= :min")
    List<Candidate> findByEducationLevelGreaterThan(@Param("min") Integer min);

    @Query(nativeQuery = true, value = "SELECT * FROM get_matching_candidates_dynamic(:requestId)")
    List<Object[]> findMatchingCandidatesDynamic(Integer requestId);

    @Query(nativeQuery = true, value = "SELECT idrecruitment_request FROM recruitment_request WHERE status IN ('pending', 'published') LIMIT 1")
    Optional<Integer> findActiveRecruitmentRequestId();
}


