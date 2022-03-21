package com.azunitech.gw1.first.application;

import com.azunitech.gw1.first.domain.EventSource;
import reactor.core.publisher.Mono;

public interface EventSourceService {
  Mono<Void> broadcast(EventSource event);
}
