package com.azunitech.rx.rxone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class RxoneApplication {
  public static void main(String[] args) {
    SpringApplication.run(RxoneApplication.class, args);
  }
}
