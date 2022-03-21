package com.azunitech.rx.rxtwo.web.rpc;

import com.azunitech.rx.rxtwo.domain.Product;
import com.azunitech.rx.rxtwo.persistent.repository.UserRepository;
import com.azunitech.rx.rxtwo.usecases.apis.CreateProduct;
import com.azunitech.rx.rxtwo.usecases.apis.FindProduct;
import com.azunitech.rx.rxtwo.web.model.ProductJson;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@AutoJsonRpcServiceImpl
@Component
public class ProductRpcServiceImpl implements ProductRpcService {
  private final FindProduct findProduct;
  private final CreateProduct createProduct;
  private final UserRepository userRepository;

  @Autowired
  public ProductRpcServiceImpl(FindProduct findProduct, CreateProduct createProduct, UserRepository userRepository) {
    this.findProduct = findProduct;
    this.createProduct = createProduct;
    this.userRepository = userRepository;
  }

  @Override
  @GetMapping
  public List<ProductJson> findProducts(String name) {
    FindProduct.Response response =
        findProduct.find(FindProduct.Request.builder().name(name).build());
    return response.getProducts().stream()
        .map(x -> converter.apply(x))
        .collect(Collectors.toList());
  }

  @Override
  public String createProduct(String name) {
    CreateProduct.Response response =
        createProduct.create(CreateProduct.Request.builder().name(name).build());
    return response.getProduct().getName();
  }

  @Override
  @GetMapping
  public List<ProductJson> findAll() {
    return userRepository.findAll().stream().map( x -> x.toDomain())
            .map(x -> ProductJson.builder().id(x.getId()).name(x.getName()).build())
            .collect(Collectors.toList());
  }

  Function<Product, ProductJson> converter =
      p -> ProductJson.builder().id(p.getId()).name(p.getName()).build();
}
