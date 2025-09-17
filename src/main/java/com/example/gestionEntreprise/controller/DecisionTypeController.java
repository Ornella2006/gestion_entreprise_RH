package com.example.gestionEntreprise.controller;

import com.example.gestionEntreprise.model.DecisionType;
import com.example.gestionEntreprise.service.DecisionTypeService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/decision-type")
public class DecisionTypeController {
    private final DecisionTypeService decisionTypeService;

    public DecisionTypeController(DecisionTypeService decisionTypeService) {
        this.decisionTypeService = decisionTypeService;
    }

    @GetMapping
    public List<DecisionType> getAll() { return decisionTypeService.getAll(); }

    @GetMapping("/{id}")
    public DecisionType getById(@PathVariable Long id) { return decisionTypeService.getById(id); }

    @PostMapping
    public DecisionType create(@RequestBody DecisionType decisionType) { return decisionTypeService.save(decisionType); }

    @PutMapping("/{id}")
    public DecisionType update(@PathVariable Long id, @RequestBody DecisionType decisionType) {
        decisionType.setIdDecision(id);
        return decisionTypeService.save(decisionType);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { decisionTypeService.delete(id); }
}
