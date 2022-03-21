package com.azunitech.gw1.first.service;

import com.azunitech.gw1.first.application.BuildUserNameService;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

@Component
public class BuildUserNameServiceImpl implements BuildUserNameService {
  private Faker faker = new Faker();

  @Override
  public String buildPersonName() {
    return faker.harryPotter().character();
  }
}
