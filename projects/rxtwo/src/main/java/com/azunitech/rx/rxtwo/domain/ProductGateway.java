package com.azunitech.rx.rxtwo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public interface ProductGateway {
  List<Product> findProduct(FindProductQuery findProductQuery);

  Product createProduct(Product product);

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  class FindProductQuery {
    private String name;
  }
}
