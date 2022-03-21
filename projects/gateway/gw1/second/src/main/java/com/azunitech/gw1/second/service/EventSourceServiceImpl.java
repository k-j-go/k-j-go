package com.azunitech.gw1.second.service;

import com.azunitech.gw1.second.application.EventSourceService;
import com.azunitech.gw1.second.application.EventSourceSink;
import com.azunitech.gw1.second.domain.EventSource;
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
