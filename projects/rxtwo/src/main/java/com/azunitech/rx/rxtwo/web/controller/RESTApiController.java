package com.azunitech.rx.rxtwo.web.controller;

import com.azunitech.rx.rxtwo.domain.Product;
import com.azunitech.rx.rxtwo.usecases.apis.RESTApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(
    value = "/products",
    produces = {MediaType.APPLICATION_JSON_VALUE})
public class RESTApiController {
  @Autowired RESTApiService restApiService;

  @GetMapping
  public List<Product> loadAll() {
    return restApiService.loadAll();
  }
}
