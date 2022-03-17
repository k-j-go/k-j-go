package com.azunitech.rx.rxtwo.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class User {

    @Id
    private Long id;

    private String firstName;
    private String lastName;

}