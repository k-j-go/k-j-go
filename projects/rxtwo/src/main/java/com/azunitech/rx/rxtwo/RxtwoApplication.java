package com.azunitech.rx.rxtwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class RxtwoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RxtwoApplication.class, args);
    }

}
