package com.azunitech.cleancode

import com.azunitech.cleancode.consumer.ConsumerEventConfig
import com.azunitech.cleancode.eventsource.EventSourceConfig
import com.azunitech.cleancode.persistent.PersistenceConfig
import com.azunitech.cleancode.usercases.UseCaseConfig
import com.azunitech.cleancode.web.WebConfig
import lombok.extern.slf4j.Slf4j
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@SpringBootApplication
class CleancodeApplication

fun main(args: Array<String>) {
    runApplication<CleancodeApplication>(*args)
}

@Slf4j
@Configuration
@Import(WebConfig::class,
        PersistenceConfig::class,
        UseCaseConfig::class,
        EventSourceConfig::class,
        ConsumerEventConfig::class)
class Wiring {


}