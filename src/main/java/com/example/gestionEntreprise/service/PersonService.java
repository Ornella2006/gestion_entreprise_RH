package com.example.gestionEntreprise.service;

import com.example.gestionEntreprise.model.Person;
import com.example.gestionEntreprise.repository.PersonRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAll() { return personRepository.findAll(); }
    public Person getById(Integer id) { return personRepository.findById(id).orElse(null); }
    public Person save(Person person) { return personRepository.save(person); }
    public void delete(Integer id) { personRepository.deleteById(id); }
}
