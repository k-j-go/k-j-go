package com.azunitech.rx.rxone;

import com.azunitech.rx.rxone.models.Person;
import com.azunitech.rx.rxone.repositories.PersonRepository;
import com.mongodb.client.model.changestream.OperationType;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
@Component
public class MyRunner implements CommandLineRunner {
  @Autowired private PersonRepository personRepository;
  @Autowired private ReactiveMongoTemplate template;

  @Override
  public void run(String... args) throws Exception {
    template
        .changeStream(Person.class)
        .listen()
        .filter(x -> x.getOperationType() == OperationType.INSERT)
        .doOnNext(x -> log.info("------- {}", x))
        .subscribe();

    List<Person> list =
        IntStream.range(1, 10000)
            .mapToObj(x -> Person.builder().id(String.format("%d", x)).age(59).name("some").build())
            .collect(Collectors.toList());
    personRepository.insert(list).subscribe();
  }
}
