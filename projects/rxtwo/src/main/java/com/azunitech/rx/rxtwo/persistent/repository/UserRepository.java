package com.azunitech.rx.rxtwo.persistent.repository;

import com.azunitech.rx.rxtwo.persistent.model.ProductDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository extends MongoRepository<ProductDocument, String> {
  public List<ProductDocument> findByName(String firstName);
}
