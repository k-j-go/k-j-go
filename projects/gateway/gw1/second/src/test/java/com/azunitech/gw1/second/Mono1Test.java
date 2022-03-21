package com.azunitech.gw1.second;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@Log4j2
public class Mono1Test {
  @Test
  public void t1Test() throws InterruptedException {

    String key = "message";
    Mono<String> r =
        Mono.just("Hello")
            .flatMap(s -> Mono.deferContextual(ctx -> Mono.just(s + " " + ctx.get(key))))
            .contextWrite(ctx -> ctx.put(key, "World"));
    StepVerifier.create(r).expectNext("Hello World").verifyComplete();
  }
}
