package com.azunitech.rx.rxtwo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
Domain Entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product {
  private String id;
  private String name;


  static class Manufactures {
    private String id;
    private String name;
    private Address address;
  }

  static class Address {
    private String State;
    private String city;
    private String country;
    private int zipCode;
  }
}
