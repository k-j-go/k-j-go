package com.azunitech.rx.rxtwo.services;

import com.azunitech.rx.rxtwo.appliation.IDGenerator;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Log4j2
@Service
public class IDGeneratorService implements IDGenerator {
  private AtomicLong atomicLong = new AtomicLong();

  @Override
  public String createID() {
    return String.format("%d", atomicLong.incrementAndGet());
  }
}
