package com.azunitech.rx.rxtwo.usecases;

import com.azunitech.rx.rxtwo.appliation.IDGenerator;
import com.azunitech.rx.rxtwo.domain.Product;
import com.azunitech.rx.rxtwo.domain.ProductGateway;
import com.azunitech.rx.rxtwo.usecases.apis.CreateProduct;
import org.springframework.stereotype.Component;

@Component
public class CreateProductImpl implements CreateProduct {
  final ProductGateway productGateway;

  private final IDGenerator idGenerator;

  public CreateProductImpl(ProductGateway productGateway, IDGenerator idGenerator) {
    this.productGateway = productGateway;
    this.idGenerator = idGenerator;
  }

  @Override
  public Response create(Request request) {
    String id = idGenerator.createID();
    Product product = Product.builder().id(id).name(request.getName()).build();
    Product createdProduct = productGateway.createProduct(product);
    return Response.builder().product(createdProduct).build();
  }
}
