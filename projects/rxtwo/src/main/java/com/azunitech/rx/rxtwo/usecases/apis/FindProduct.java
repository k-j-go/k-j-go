package com.azunitech.rx.rxtwo.usecases.apis;

import com.azunitech.rx.rxtwo.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public interface FindProduct {
  Response find(Request request);

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  class Response {
      private List<Product> products;
  }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
  class Request {
      private String name;
  }
}
