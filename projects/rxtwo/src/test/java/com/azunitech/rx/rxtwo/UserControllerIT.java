package com.azunitech.rx.rxtwo;

import com.azunitech.rx.rxtwo.persistent.model.ProductDocument;
import com.azunitech.rx.rxtwo.persistent.repository.UserRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@DataMongoTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerIT {

  @Autowired private UserRepository userRepository;

  private String name;

  @BeforeAll
  public void setup() {

    Faker faker = new Faker();
    ProductDocument product =
        userRepository.save(ProductDocument.builder().id("1").name("James").build());
    name = product.getName();
  }

  @Test
  public void test_getById_successfull() throws Exception {
    Assertions.assertEquals("James", userRepository.findByName(name).get(0).getName());
  }
}
