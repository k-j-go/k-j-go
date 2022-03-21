package com.azunitech.rx.rxtwo.web.rpc;

import com.azunitech.rx.rxtwo.web.model.ProductJson;
import com.googlecode.jsonrpc4j.JsonRpcService;

import java.util.List;

@JsonRpcService("/rpc/product")
public interface  ProductRpcService{
   List<ProductJson> findProducts(String name);
   String createProduct( String name);
   List<ProductJson> findAll();
}



