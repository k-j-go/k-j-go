package com.azunitech.rx.rxone;

import com.azunitech.rx.rxone.models.Person;
import com.azunitech.rx.rxone.repositories.PersonRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
@Component
public class MyRunner implements CommandLineRunner {
  @Autowired private PersonRepository personRepository;

  @Override
  public void run(String... args) throws Exception {
    List<Person> list =
        IntStream.range(1, 10000)
            .mapToObj(x -> Person.builder().id(String.format("%d", x)).age(59).name("some").build())
            .collect(Collectors.toList());
    personRepository.insert(list).subscribe();
  }
}
