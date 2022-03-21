package com.azunitech.gw1.second.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class GoldenGlobalFilter implements GlobalFilter, Ordered {
  final Logger logger = LoggerFactory.getLogger(GoldenGlobalFilter.class);

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    logger.info("---- Global Pre Filter executed");
    return chain.filter(exchange);
  }

  @Override
  public int getOrder() {
    return 10;
  }
}
