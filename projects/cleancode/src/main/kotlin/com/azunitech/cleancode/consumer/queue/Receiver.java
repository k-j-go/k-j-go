package com.azunitech.cleancode.consumer.queue;

import com.azunitech.cleancode.consumer.NoticeListener;
import com.azunitech.cleancode.usercases.apis.ConsumeEvent;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Receiver {
  private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

  @Autowired private NoticeListener noticeListener;

  @KafkaListener(topics = "${app.topic.success}")
  public void listen(@Payload String message) {
    Gson gson = new Gson();
    ConsumeEvent.ProductCreatedEvent obj = gson.fromJson(message, ConsumeEvent.ProductCreatedEvent.class);
    noticeListener.notice(obj);
    logger.info(String.format("get %s from kafka", obj.getId()));
  }
}
