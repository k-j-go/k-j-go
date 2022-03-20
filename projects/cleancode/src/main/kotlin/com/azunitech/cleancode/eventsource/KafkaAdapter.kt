package com.azunitech.cleancode.eventsource

import com.azunitech.cleancode.eventsource.kafka.Sender
import com.azunitech.cleancode.usercases.apis.NotifyEvent
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Slf4j
@Component
class KafkaAdapter() : NotifyEvent {
    companion object {
        val logger = LoggerFactory.getLogger("KafkaAdapter")
    }

    @Autowired
    lateinit var sender: Sender

    override fun notice(events: NotifyEvent.ProductCreatedEvent) {
        logger.info(events.id)
        var gson = Gson()
        val payload = gson.toJson(events);
        sender.send(payload, events.id)
    }
}
