package com.azunitech.gw1.second.gateway.predicators.golden;

import com.azunitech.gw1.second.gateway.services.GoldenCustomerService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.function.Predicate;

@Component
public class GoldenCustomerRoutePredicateFactory
    extends AbstractRoutePredicateFactory<GoldenCustomerRoutePredicateFactory.Config> {

  private final GoldenCustomerService goldenCustomerService;

  public GoldenCustomerRoutePredicateFactory(GoldenCustomerService goldenCustomerService) {
    super(Config.class);
    this.goldenCustomerService = goldenCustomerService;
  }

  // ... constructor omitted

  @Override
  public Predicate<ServerWebExchange> apply(Config config) {
    return (ServerWebExchange t) -> {
      List<String> headers = t.getRequest().getHeaders().get(config.customerId);

      boolean isGolden;
      if (headers == null || headers.isEmpty()) {
        isGolden = false;
      } else {
        String customerId = headers.get(0);
        Mono<Long> mono = goldenCustomerService.isGoldenCustomer(customerId);
      }
      return config.isGolden();
    };
  }

  @Component
  @Validated
  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Config {
    boolean isGolden = true;
    @NotEmpty String customerId = "customerId";
    // ...constructors and mutators omitted
  }
}
