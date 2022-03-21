package com.azunitech.rx.rxtwo.persistent.utils;

import com.azunitech.rx.rxtwo.domain.Product;
import com.azunitech.rx.rxtwo.persistent.model.ProductDocument;

import java.util.function.Function;

public class Utils {
  public static Function<Product, ProductDocument> converter =
      user -> ProductDocument.builder().name(user.getName()).id(user.getId()).build();
}
