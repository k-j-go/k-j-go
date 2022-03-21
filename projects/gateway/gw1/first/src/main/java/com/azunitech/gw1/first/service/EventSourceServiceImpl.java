package com.azunitech.gw1.first.service;

import com.azunitech.gw1.first.application.EventSourceService;
import com.azunitech.gw1.first.application.EventSourceSink;
import com.azunitech.gw1.first.domain.EventSource;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class EventSourceServiceImpl implements EventSourceService {
  final EventSourceSink sink;

  public EventSourceServiceImpl(EventSourceSink sink) {
    this.sink = sink;
  }

  @Override
  public Mono<Void> broadcast(EventSource event) {
    return null;
  }
}
