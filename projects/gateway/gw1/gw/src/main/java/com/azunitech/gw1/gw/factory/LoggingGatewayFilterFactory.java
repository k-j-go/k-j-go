package com.azunitech.gw1.gw.factory;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Log4j2
@Component
public class LoggingGatewayFilterFactory
    extends AbstractGatewayFilterFactory<LoggingGatewayFilterFactory.Config> {
  public LoggingGatewayFilterFactory() {
    super(Config.class);
  }

  @Override
  public GatewayFilter apply(Config config) {
    return (exchange, chain) -> {
      // Pre-processing
      if (config.isPreLogger()) {
        log.info("---- Pre GatewayFilter logging: " + config.getBaseMessage());
      }
      return chain
          .filter(exchange)
          .then(
              Mono.fromRunnable(
                  () -> {
                    // Post-processing
                    if (config.isPostLogger()) {
                      log.info("---- Post GatewayFilter logging: " + config.getBaseMessage());
                    }
                  }));
    };
  }

  @Component
  @Data
  public static class Config {
    private String baseMessage;
    private boolean preLogger;
    private boolean postLogger;

    // contructors, getters and setters...
  }
}
