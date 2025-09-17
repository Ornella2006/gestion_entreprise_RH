package com.example.gestionEntreprise.service;

import com.example.gestionEntreprise.model.ProbationStatus;
import com.example.gestionEntreprise.repository.ProbationStatusRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProbationStatusService {
    private final ProbationStatusRepository probationStatusRepository;

    public ProbationStatusService(ProbationStatusRepository probationStatusRepository) {
        this.probationStatusRepository = probationStatusRepository;
    }

    public List<ProbationStatus> getAll() { return probationStatusRepository.findAll(); }
    public ProbationStatus getById(Long id) { return probationStatusRepository.findById(id).orElse(null); }
    public ProbationStatus save(ProbationStatus probationStatus) { return probationStatusRepository.save(probationStatus); }
    public void delete(Long id) { probationStatusRepository.deleteById(id); }
}
