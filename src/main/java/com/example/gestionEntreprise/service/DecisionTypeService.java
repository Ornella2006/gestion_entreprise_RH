package com.example.gestionEntreprise.service;

import com.example.gestionEntreprise.model.DecisionType;
import com.example.gestionEntreprise.repository.DecisionTypeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DecisionTypeService {
    private final DecisionTypeRepository decisionTypeRepository;

    public DecisionTypeService(DecisionTypeRepository decisionTypeRepository) {
        this.decisionTypeRepository = decisionTypeRepository;
    }

    public List<DecisionType> getAll() { return decisionTypeRepository.findAll(); }
    public DecisionType getById(Long id) { return decisionTypeRepository.findById(id).orElse(null); }
    public DecisionType save(DecisionType decisionType) { return decisionTypeRepository.save(decisionType); }
    public void delete(Long id) { decisionTypeRepository.deleteById(id); }
}
