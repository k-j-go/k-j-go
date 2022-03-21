package com.azunitech.gw1.second.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventSource {
  private Date time;
  private User user;
  private EventSourceType eventSourceType;

  public enum EventSourceType {
    CREATE
  }
}
