package com.azunitech.gw1.second.gateway.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class ModifyResponseGatewayFilterFactory
    extends AbstractGatewayFilterFactory<ModifyResponseGatewayFilterFactory.Config> {

  final Logger logger = LoggerFactory.getLogger(ModifyResponseGatewayFilterFactory.class);

  public ModifyResponseGatewayFilterFactory() {
    super(Config.class);
  }

  @Override
  public GatewayFilter apply(Config config) {
    return (ServerWebExchange exchange, GatewayFilterChain chain) -> {
      return chain
          .filter(exchange)
          .then(
              Mono.fromRunnable(
                  () -> {
                    ServerHttpResponse response = exchange.getResponse();
                    response.getHeaders().add("my-Custom-Language-Header", "test");
                    logger.info("---- Added custom header to Response");
                  }));
    };
  }

  public static class Config {}
}
