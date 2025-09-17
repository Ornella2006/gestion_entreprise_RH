package com.example.gestionEntreprise.controller;

import com.example.gestionEntreprise.model.Actor;
import com.example.gestionEntreprise.service.ActorService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/actor")
public class ActorController {
    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public List<Actor> getAll() { return actorService.getAll(); }

    @GetMapping("/{id}")
    public Actor getById(@PathVariable Long id) { return actorService.getById(id); }

    @PostMapping
    public Actor create(@RequestBody Actor actor) { return actorService.save(actor); }

    @PutMapping("/{id}")
    public Actor update(@PathVariable Long id, @RequestBody Actor actor) {
        actor.setIdActor(id);
        return actorService.save(actor);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { actorService.delete(id); }
}
