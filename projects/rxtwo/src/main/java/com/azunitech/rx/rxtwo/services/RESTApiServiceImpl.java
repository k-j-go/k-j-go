package com.azunitech.rx.rxtwo.services;

import com.azunitech.rx.rxtwo.domain.Product;
import com.azunitech.rx.rxtwo.persistent.model.ProductDocument;
import com.azunitech.rx.rxtwo.persistent.repository.UserRepository;
import com.azunitech.rx.rxtwo.usecases.apis.RESTApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RESTApiServiceImpl implements RESTApiService {
  @Autowired UserRepository repository;

  @Override
  public List<Product> loadAll() {
    return repository.findAll().stream()
        .map(ProductDocument::toDomain)
        .collect(Collectors.toList());
  }
}
