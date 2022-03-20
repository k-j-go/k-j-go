package com.azunitech.rx.rxone.controllers;

import com.azunitech.rx.rxone.models.Person;
import com.azunitech.rx.rxone.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.stream.IntStream;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class RoutesConfiguration {

  private static final Logger log = LoggerFactory.getLogger(RoutesConfiguration.class);

  @SuppressWarnings("SpringJavaAutowiringInspection")
  @Bean
  RouterFunction<?> routes(PersonRepository personRepository) {
    return route(
            GET("/person/{id}"),
            request ->
                ok().body(personRepository.findById(request.pathVariable("id")), Person.class))
        .andRoute(
            POST("/person"),
            request ->
                personRepository
                    .insert(request.bodyToMono(Person.class))
                    .next()
                    .flatMap(
                        s ->
                            ok().contentType(MediaType.APPLICATION_JSON)
                                .body(Mono.just("Post Successful"), String.class))
                    .onErrorResume(
                        Exception.class,
                        e -> {
                          log.error("Exception: ", e);
                          return ServerResponse.status(HttpStatus.CONFLICT)
                              .contentType(MediaType.APPLICATION_JSON)
                              .body(
                                  Mono.just(
                                      "Exception occurred while inserting data: " + e.getMessage()),
                                  String.class);
                        }))

        // Just for testing another route
        .andRoute(
            GET("/test"),
            request ->
                ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just("test"), String.class))
        .andRoute(
            GET("/create"),
            request -> {
              IntStream.range(1, 100)
                  .forEach(
                      x -> {
                        personRepository
                            .insert(
                                Mono.just(
                                    Person.builder()
                                        .name("tt")
                                        .age(x)
                                        .id(String.format("%d", x))
                                        .build()))
                            .subscribe();
                      });
              return ok().contentType(MediaType.APPLICATION_JSON)
                  .body(Mono.just("created"), String.class);
            })
        .andRoute(
            GET("/demoPost"),
            request -> {
              Person person = new Person();
              person.setAge(32);
              person.setId("1");
              person.setName("Test");
              WebClient.RequestHeadersSpec<?> request1 =
                  WebClient.create()
                      .post()
                      .uri("http://localhost:8080/person")
                      .body(BodyInserters.fromObject(person))
                      .header("Content-Type", "application/json")
                      .header("Authorization", "Basic dXNlcjpwYXNzd29yZA==");
              return request1
                  .exchange()
                  .flatMap(
                      s ->
                          ok().contentType(MediaType.APPLICATION_JSON)
                              .body(s.bodyToMono(String.class), String.class))
                  .onErrorResume(
                      Exception.class,
                      e -> {
                        log.error("Exception: ", e);
                        return ServerResponse.status(HttpStatus.CONFLICT)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(Mono.just("Exception occurred: " + e.getMessage()), String.class);
                      });
            });
  }
}
