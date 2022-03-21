package com.azunitech.gw1.gw;

import com.azunitech.gw1.gw.factory.ModifyResponseGatewayFilterFactory;
import com.azunitech.gw1.gw.predicators.golden.GoldenCustomerRoutePredicateFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
  @Bean
  public RouteLocator customRouteLocator(
      RouteLocatorBuilder builder,
      GoldenCustomerRoutePredicateFactory gf,
      ModifyResponseGatewayFilterFactory gwf) {
    return builder
        .routes()
        .route(
            "about_route",
            r -> {
              r.predicate(
                  gf.apply(new GoldenCustomerRoutePredicateFactory.Config(false, "x-customerId")));
              return r.path("/about").uri("http://127.0.0.1:8080");
            })
        .build();
  }
}
