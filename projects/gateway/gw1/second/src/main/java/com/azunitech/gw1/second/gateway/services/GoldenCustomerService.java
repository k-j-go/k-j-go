package com.azunitech.gw1.second.gateway.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Log4j2
@Service
public class GoldenCustomerService {

  @Value("${golden.url}")
  private String BASE_URL;

  public Mono<Long> isGoldenCustomer(String customerId) {
    return WebClient.builder()
        .baseUrl(BASE_URL)
        .build()
        .get()
        .uri("customer")
        .retrieve()
        .bodyToFlux(Customer.class)
        .filter(x -> x.getCustomerId().compareTo(customerId) == 0)
        .doOnNext(log::info)
        .count();
  }
}
