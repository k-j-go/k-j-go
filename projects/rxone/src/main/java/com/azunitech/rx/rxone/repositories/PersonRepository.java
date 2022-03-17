package com.azunitech.rx.rxone.repositories;


import com.azunitech.rx.rxone.models.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface PersonRepository extends ReactiveMongoRepository<Person, String> {
    Flux<Person> findByName(String name);
}
