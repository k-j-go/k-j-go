package com.azunitech.rx.rxone;

import com.azunitech.rx.rxone.models.Person;
import com.azunitech.rx.rxone.repositories.PersonRepository;
import com.mongodb.client.model.changestream.OperationType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
@EnableReactiveMongoRepositories
@RequiredArgsConstructor
@Log4j2
public class RxoneApplication {
  public static void main(String[] args) {
    SpringApplication.run(RxoneApplication.class, args);
  }

  @Autowired private PersonRepository personRepository;
  @Autowired private ReactiveMongoTemplate template;

  @Bean
  public ApplicationRunner applicationRunner() {
    return args -> {
      template
          .changeStream(Person.class)
          .listen()
          .filter(x -> x.getOperationType() == OperationType.INSERT)
          .doOnNext(x -> log.info("------- {}", x))
          .subscribe();

      List<Person> list =
          IntStream.range(1, 10)
              .mapToObj(
                  x -> Person.builder().id(String.format("%d", x)).age(59).name("some").build())
              .collect(Collectors.toList());
      personRepository.insert(list).subscribe();
    };
  }
}
