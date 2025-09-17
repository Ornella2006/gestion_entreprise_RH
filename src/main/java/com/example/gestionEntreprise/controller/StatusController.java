package com.example.gestionEntreprise.controller;

import com.example.gestionEntreprise.model.Status;
import com.example.gestionEntreprise.service.StatusService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/status")
public class StatusController {
    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    public List<Status> getAll() { return statusService.getAll(); }

    @GetMapping("/{id}")
    public Status getById(@PathVariable Long id) { return statusService.getById(id); }

    @PostMapping
    public Status create(@RequestBody Status status) { return statusService.save(status); }

    @PutMapping("/{id}")
    public Status update(@PathVariable Long id, @RequestBody Status status) {
        status.setIdStatus(id);
        return statusService.save(status);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { statusService.delete(id); }
}
