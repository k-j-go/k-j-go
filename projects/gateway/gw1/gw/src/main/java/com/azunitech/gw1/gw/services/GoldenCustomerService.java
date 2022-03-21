package com.azunitech.gw1.gw.services;

import lombok.extern.log4j.Log4j2;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Log4j2
@Service
public class GoldenCustomerService {

  @Value("${golden.url}")
  private String BASE_URL;

  public boolean isGoldenCustomer(String customerId) {
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder().url(BASE_URL).build();

    try {
      Response response = client.newCall(request).execute();
      String count = Objects.requireNonNull(response.body()).string();
      log.info(count);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return true;
  }
}
