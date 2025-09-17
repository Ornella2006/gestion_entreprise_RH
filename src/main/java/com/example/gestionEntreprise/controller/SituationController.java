package com.example.gestionEntreprise.controller;

import com.example.gestionEntreprise.model.Situation;
import com.example.gestionEntreprise.service.SituationService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/situation")
public class SituationController {
    private final SituationService situationService;

    public SituationController(SituationService situationService) {
        this.situationService = situationService;
    }

    @GetMapping
    public List<Situation> getAll() { return situationService.getAll(); }

    @GetMapping("/{id}")
    public Situation getById(@PathVariable Long id) { return situationService.getById(id); }

    @PostMapping
    public Situation create(@RequestBody Situation situation) { return situationService.save(situation); }

    @PutMapping("/{id}")
    public Situation update(@PathVariable Long id, @RequestBody Situation situation) {
        situation.setIdSituation(id);
        return situationService.save(situation);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { situationService.delete(id); }
}
