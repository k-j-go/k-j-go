package com.azunitech.rx.rxtwo.usecases.apis;

import com.azunitech.rx.rxtwo.domain.Product;

import java.util.List;

public interface RESTApiService {
    List<Product> loadAll();
}
