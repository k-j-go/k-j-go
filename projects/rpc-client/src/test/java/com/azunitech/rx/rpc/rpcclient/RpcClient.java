package com.azunitech.rx.rpc.rpcclient;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.ArrayList;

public class RpcClient {
  @Test
  public void testRpcCallCreateProduct() throws Throwable {
    JsonRpcHttpClient client = new JsonRpcHttpClient(new URL("http://localhost:8080/rpc/product"));
    String name = client.invoke("createProduct", new Object[] {"apple"}, String.class);
    Assert.assertEquals("apple", name);
  }

  @Test
  public void testRpcCallFindAll() throws Throwable {
    JsonRpcHttpClient client = new JsonRpcHttpClient(new URL("http://localhost:8080/rpc/product"));
    ArrayList<Process> name = client.invoke("findAll", new Object[] {}, ArrayList.class);
    Assert.assertEquals("apple", name);
  }

  @Test
  public void testRpcCallFindProduct() throws Throwable {
    JsonRpcHttpClient client = new JsonRpcHttpClient(new URL("http://localhost:8080/rpc/product"));

    ProductJson productJson =
        client.invoke("findProducts", new Object[] {"apple"}, Json ProductJson.class);
    Assert.assertEquals("apple", productJson.getName());
  }
}
