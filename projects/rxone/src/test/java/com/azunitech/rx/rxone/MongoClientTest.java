package com.azunitech.rx.rxone;

import com.azunitech.rx.rxone.models.Person;
import com.azunitech.rx.rxone.repositories.PersonRepository;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

import java.net.UnknownHostException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoClientTest {
  @Autowired MongoClient mongoClient;
  @Autowired ReactiveMongoOperations operations;
  @Autowired PersonRepository repository;

  @Before
  public void setUp() throws UnknownHostException {

    operations
        .collectionExists(Person.class)
        .flatMap(exists -> exists ? operations.dropCollection(Person.class) : Mono.just(exists))
        .flatMap(
            o ->
                operations.createCollection(
                    Person.class, new CollectionOptions(1024L * 1024L, 100L, true)))
        .then()
        .block();
  }

  @Test
  public void checkDB() {
    MongoDatabase db = this.mongoClient.getDatabase("test");
    MongoCollection<Person> collection = db.getCollection("person", Person.class);
    collection
        .watch()
        .first()
        .subscribe(
            new Subscriber<ChangeStreamDocument<Document>>() {
              @Override
              public void onSubscribe(Subscription subscription) {}

              @Override
              public void onNext(ChangeStreamDocument<Document> documentChangeStreamDocument) {}

              @Override
              public void onError(Throwable throwable) {}

              @Override
              public void onComplete() {}
            });

    Person p = Person.builder().age(44).id(String.format("%d", 1)).name("test").build();
    repository.save(p).then().block();
  }
}
