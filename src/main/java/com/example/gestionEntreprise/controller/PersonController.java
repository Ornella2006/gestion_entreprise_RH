package com.example.gestionEntreprise.controller;

import com.example.gestionEntreprise.model.Person;
import com.example.gestionEntreprise.service.PersonService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getAll() { return personService.getAll(); }

    @GetMapping("/{id}")
    public Person getById(@PathVariable Integer id) { return personService.getById(id); }

    @PostMapping
    public Person create(@RequestBody Person person) { return personService.save(person); }

    @PutMapping("/{id}")
    public Person update(@PathVariable Long id, @RequestBody Person person) {
        person.setIdPerson(id);
        return personService.save(person);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) { personService.delete(id); }
}
