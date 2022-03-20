package com.azunitech.cleancode.consumer

import com.azunitech.cleancode.usercases.apis.ConsumeEvent
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Component


@Slf4j
@Component
class NoticeListener() : ConsumeEvent {
    override fun notice(events: ConsumeEvent.ProductCreatedEvent) {

    }
}