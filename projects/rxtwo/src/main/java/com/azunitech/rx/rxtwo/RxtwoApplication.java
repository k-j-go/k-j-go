package com.azunitech.rx.rxtwo;

import com.azunitech.rx.rxtwo.appliation.IDGenerator;
import com.azunitech.rx.rxtwo.persistent.PersistentConfig;
import com.azunitech.rx.rxtwo.persistent.model.ProductDocument;
import com.azunitech.rx.rxtwo.persistent.repository.UserRepository;
import com.azunitech.rx.rxtwo.services.ServiceConfig;
import com.azunitech.rx.rxtwo.usecases.UseCasesConfig;
import com.azunitech.rx.rxtwo.web.WebConfig;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.util.stream.IntStream;

@SpringBootApplication
@Import({WebConfig.class, PersistentConfig.class, UseCasesConfig.class, ServiceConfig.class})
public class RxtwoApplication {

  public static void main(String[] args) {
    SpringApplication.run(RxtwoApplication.class, args);
  }

  @Autowired UserRepository userRepository;
  @Autowired IDGenerator idGenerator;

  @Bean
  public ApplicationRunner getRunner() {
    Faker faker = new Faker();
    return args -> {
      IntStream.range(1, 10)
          .forEach(
              x -> {
                String id = idGenerator.createID();
                userRepository.insert(
                    ProductDocument.builder().name(faker.food().fruit()).id(id).build());
              });
    };
  }
}
