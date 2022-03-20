package com.azunitech.rx.rxone.controllers;

import com.azunitech.rx.rxone.models.Person;
import com.azunitech.rx.rxone.repositories.PersonRepository;
import com.mongodb.client.model.changestream.OperationType;
import com.mongodb.reactivestreams.client.MongoClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@RestController
@RequestMapping("/person")
public class PersonController {
  private final PersonRepository personRepository;
  private final ReactiveMongoTemplate template;
  private final MongoClient mongoClient;

  public PersonController(
      PersonRepository personRepository, ReactiveMongoTemplate template, MongoClient mongoClient) {
    this.personRepository = personRepository;
    this.template = template;
    this.mongoClient = mongoClient;
    this.template
        .changeStream(Person.class)
        .listen()
        .filter(x -> x.getOperationType() == OperationType.REPLACE)
        .doOnNext(x -> log.info("------- {}", x))
        .subscribe();
  }

  @GetMapping
  public Flux<Person> index() {
    return personRepository.findAll();
  }

  @PutMapping("/{id}")
  public Mono<Person> save(@PathVariable String id, @RequestBody Person person) {
    return personRepository.save(person);
  }
}
