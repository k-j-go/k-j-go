package com.azunitech.gw1.gw;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class Flux1 {

  private List<String> ifhrIds() {
    return Arrays.asList("id1", "id2");
  }

  private Mono<String> ifhrName(String id) {
    return Mono.just("NameJoe");
  }

  private Mono<Integer> ifhrStat(String id) {
    return Mono.just(102);
  }

  @Test
  public void g1Test() {
    Flux<String> combinations =
        Flux.fromIterable(ifhrIds())
            .flatMap(
                id -> {
                  Mono<String> nameTask = ifhrName(id);
                  Mono<Integer> statTask = ifhrStat(id);
                  return nameTask.zipWith(
                      statTask, (name, stat) -> "Name " + name + " has stats " + stat);
                });

    Mono<List<String>> result = combinations.collectList();

    List<String> results = result.block();
    assert results != null;
    results.forEach(log::info);
  }

  @Test
  public void g2Test() {
    Mono.just("Bill")
        .zipWith(Mono.just("Li"), (n1, n2) -> String.format("%s, %s", n1, n2))
        .doOnNext(x -> log.info(x))
        .subscribe();
  }
}
