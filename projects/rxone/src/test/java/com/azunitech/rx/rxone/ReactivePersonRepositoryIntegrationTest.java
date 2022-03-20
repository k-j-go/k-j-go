package com.azunitech.rx.rxone;

import com.azunitech.rx.rxone.models.Person;
import com.azunitech.rx.rxone.repositories.PersonRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReactivePersonRepositoryIntegrationTest {

  @Autowired PersonRepository repository;
  @Autowired ReactiveMongoOperations operations;
  @Autowired MongoClient mongoClient;

  @Before
  public void setUp() {
    operations
        .collectionExists(Person.class)
        .flatMap(exists -> exists ? operations.dropCollection(Person.class) : Mono.just(exists))
        .flatMap(
            o ->
                operations.createCollection(
                    Person.class, new CollectionOptions(1024L * 1024L, 100L, true)))
        .then()
        .block();

    Person p = Person.builder().age(44).id(String.format("%d", 1)).name("test").build();
    repository.save(p).then().block();
  }

  @Test
  public void findByNameAndImageUrlWithStringQueryTest() {
    Person mug = repository.findById(Mono.just("1")).block();
    assertThat(mug).isNotNull();
  }
}
