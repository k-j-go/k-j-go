package com.azunitech.rx.rxtwo.persistent;

import com.azunitech.rx.rxtwo.domain.Product;
import com.azunitech.rx.rxtwo.domain.ProductGateway;
import com.azunitech.rx.rxtwo.persistent.model.ProductDocument;
import com.azunitech.rx.rxtwo.persistent.repository.UserRepository;
import com.azunitech.rx.rxtwo.persistent.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
class ProductGatewayImpl implements ProductGateway {
  UserRepository repository;

  @Autowired
  public ProductGatewayImpl(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<Product> findProduct(FindProductQuery findUserQuery) {
    return repository.findByName(findUserQuery.getName()).stream()
        .map(ProductDocument::toDomain)
        .collect(Collectors.toList());
  }

  @Override
  public Product createProduct(Product product) {
    ProductDocument document = Utils.converter.apply(product);
    return repository.insert(document).toDomain();
  }
}
