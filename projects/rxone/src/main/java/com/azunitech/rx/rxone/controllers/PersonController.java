package com.azunitech.rx.rxone.controllers;

import com.azunitech.rx.rxone.models.Person;
import com.azunitech.rx.rxone.repositories.PersonRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public Flux<Person> index() {
        return personRepository.findAll();
    }
}
