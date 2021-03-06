package com.azunitech.rx.rxone.controllers;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

@Component
@Order(-2)
public class ExceptionHandler implements WebExceptionHandler {
  @Override
  public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
    return null;
  }
}
