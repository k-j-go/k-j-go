package com.azunitech.rx.rxtwo.usecases;

import com.azunitech.rx.rxtwo.domain.Product;
import com.azunitech.rx.rxtwo.domain.ProductGateway;
import com.azunitech.rx.rxtwo.usecases.apis.FindProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindProductImpl implements FindProduct {
  @Autowired ProductGateway productGateway;

  @Override
  public Response find(Request request) {
    ProductGateway.FindProductQuery find = ProductGateway.FindProductQuery.builder().name(request.getName()).build();
    List<Product> products = productGateway.findProduct(find);
    return Response.builder().products(products).build();
  }
}
