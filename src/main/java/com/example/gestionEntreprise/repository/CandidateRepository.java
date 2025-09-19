package com.example.gestionEntreprise.repository;

import com.example.gestionEntreprise.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    @Query("SELECT c FROM Candidate c JOIN c.person p WHERE p.email = :email")
    Candidate findByPersonEmail(@Param("email") String email);

    @Query("SELECT c FROM Candidate c WHERE c.person.idPerson = :personId")
    Candidate findByPersonId(@Param("personId") Long personId);
}


