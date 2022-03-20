package com.azunitech.cleancode.eventsource.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {
    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${app.topic.success}")
    private String success_topic;


    public void send(String payload, String sku_id) {
        kafkaTemplate.send(success_topic, payload);
        LOGGER.info("sent payload='{}' to topic='{}'", sku_id, success_topic);
    }
}
