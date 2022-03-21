package com.azunitech.gw1.second.kafka;

import com.azunitech.gw1.second.application.EventSourceSink;
import com.azunitech.gw1.second.domain.EventSource;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Log4j2
@Component
public class KafkaEventSourceService implements EventSourceSink {
  @Override
  public Mono<EventSource> sink(EventSource event) {
    return Mono.just(event).flatMap(Mono::just).doOnNext(log::info);
  }
}
