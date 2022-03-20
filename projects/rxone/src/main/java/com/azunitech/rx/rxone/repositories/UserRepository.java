package com.azunitech.rx.rxone.repositories;

import com.azunitech.rx.rxone.models.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {
  Flux<User> findByFirstName(String firstName);
}
