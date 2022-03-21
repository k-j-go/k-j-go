package com.azunitech.rx.rxtwo.usecases.apis;

import com.azunitech.rx.rxtwo.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface CreateProduct {
  Response create(Request request);

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  class Response {
    private Product product;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  class Request {
    private String name;
  }
}
