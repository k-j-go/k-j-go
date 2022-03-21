package com.azunitech.gw1.gw.predicators.method;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.function.Predicate;

/** predicate should be as below - name: MyMethod args: myMethod: GET */
@Component
public class MyMethodRoutePredicateFactory
    extends AbstractRoutePredicateFactory<MyMethodRoutePredicateFactory.Config> {
  public MyMethodRoutePredicateFactory() {
    super(Config.class);
  }

  @Override
  public Predicate<ServerWebExchange> apply(Config config) {
    return exchange -> {
      HttpMethod requestMethod = exchange.getRequest().getMethod();
      return requestMethod == config.getMyMethod();
    };
  }

  @Component
  @Validated
  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Config {
    private HttpMethod myMethod;
  }
}
