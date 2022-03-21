package com.azunitech.gw1.second.application;

import com.azunitech.gw1.second.domain.EventSource;
import reactor.core.publisher.Mono;

public interface EventSourceService {
  Mono<Void> broadcast(EventSource event);
}
