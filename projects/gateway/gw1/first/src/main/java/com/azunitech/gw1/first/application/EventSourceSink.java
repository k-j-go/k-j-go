package com.azunitech.gw1.first.application;

import com.azunitech.gw1.first.domain.EventSource;
import reactor.core.publisher.Mono;

public interface EventSourceSink {
  Mono<EventSource> sink(EventSource event);
}
