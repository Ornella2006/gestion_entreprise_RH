package com.example.gestionEntreprise.service;

import com.example.gestionEntreprise.model.Status;
import com.example.gestionEntreprise.repository.StatusRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StatusService {
    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<Status> getAll() { return statusRepository.findAll(); }
    public Status getById(Long id) { return statusRepository.findById(id).orElse(null); }
    public Status save(Status status) { return statusRepository.save(status); }
    public void delete(Long id) { statusRepository.deleteById(id); }
}
