package com.example.gestionEntreprise.service;

import com.example.gestionEntreprise.model.Situation;
import com.example.gestionEntreprise.repository.SituationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SituationService {
    private final SituationRepository situationRepository;

    public SituationService(SituationRepository situationRepository) {
        this.situationRepository = situationRepository;
    }

    public List<Situation> getAll() { return situationRepository.findAll(); }
    public Situation getById(Long id) { return situationRepository.findById(id).orElse(null); }
    public Situation save(Situation situation) { return situationRepository.save(situation); }
    public void delete(Long id) { situationRepository.deleteById(id); }
}
