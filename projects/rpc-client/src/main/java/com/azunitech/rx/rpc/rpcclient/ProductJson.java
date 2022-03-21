package com.azunitech.rx.rpc.rpcclient;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductJson {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
}
