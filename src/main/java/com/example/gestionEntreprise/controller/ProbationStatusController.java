package com.example.gestionEntreprise.controller;

import com.example.gestionEntreprise.model.ProbationStatus;
import com.example.gestionEntreprise.service.ProbationStatusService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/probation-status")
public class ProbationStatusController {
    private final ProbationStatusService probationStatusService;

    public ProbationStatusController(ProbationStatusService probationStatusService) {
        this.probationStatusService = probationStatusService;
    }

    @GetMapping
    public List<ProbationStatus> getAll() { return probationStatusService.getAll(); }

    @GetMapping("/{id}")
    public ProbationStatus getById(@PathVariable Long id) { return probationStatusService.getById(id); }

    @PostMapping
    public ProbationStatus create(@RequestBody ProbationStatus probationStatus) { return probationStatusService.save(probationStatus); }

    @PutMapping("/{id}")
    public ProbationStatus update(@PathVariable Long id, @RequestBody ProbationStatus probationStatus) {
        probationStatus.setIdProbationStatus(id);
        return probationStatusService.save(probationStatus);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { probationStatusService.delete(id); }
}
