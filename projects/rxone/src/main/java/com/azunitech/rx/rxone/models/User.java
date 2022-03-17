package com.azunitech.rx.rxone.models;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@ToString
@Document
public class User {

    @Id
    private Long id;

    private String firstName;
    private String lastName;

}