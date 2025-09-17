package com.example.gestionEntreprise.service;

import com.example.gestionEntreprise.model.Actor;
import com.example.gestionEntreprise.repository.ActorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ActorService {
    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<Actor> getAll() { return actorRepository.findAll(); }
    public Actor getById(Long id) { return actorRepository.findById(id).orElse(null); }
    public Actor save(Actor actor) { return actorRepository.save(actor); }
    public void delete(Long id) { actorRepository.deleteById(id); }
}
