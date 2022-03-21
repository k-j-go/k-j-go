package com.azunitech.gw1.gw;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Log4j2
public class flux {

  @Test
  public void g1Tsst() {
    Mono.fromSupplier(() -> 100)
        .contextWrite(ctx -> ctx.put("TEST", "TEST"))
        .doOnNext(log::info)
        .subscribe();
  }

  @Test
  public void g2Test() {
    String key = "message";
    Mono<String> r =
        Mono.just("Hello")
            .flatMap(s -> Mono.deferContextual(ctx -> Mono.just(s + " " + ctx.get(key))))
            .contextWrite(ctx -> ctx.put(key, "World"));
    StepVerifier.create(r).expectNext("Hello World").verifyComplete();
  }

  @Test
  public void g3Test() {

    Flux.just("a", "b", "c", "d")
        .flatMap(Mono::just)
        .switchIfEmpty(Mono.just("a"))
        .flatMap(s -> Mono.deferContextual(ctx -> Mono.just(s + " " + ctx.get("a"))))
        .contextWrite(ctx -> ctx.put("a", "a"))
        .doOnNext(log::info)
        .subscribe();
  }

  @Test
  public void g4Test() throws InterruptedException, ExecutionException {
    log.info(calculateAsync().get());
  }

  public Future<String> calculateAsync() throws InterruptedException {
    CompletableFuture<String> completableFuture = new CompletableFuture<>();
    Executors.newCachedThreadPool()
        .submit(
            () -> {
              Thread.sleep(500);
              completableFuture.complete("Hello");
              return null;
            });
    return completableFuture;
  }
}
