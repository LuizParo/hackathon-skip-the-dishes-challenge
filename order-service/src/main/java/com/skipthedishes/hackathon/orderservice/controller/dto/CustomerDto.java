package com.skipthedishes.hackathon.orderservice.controller.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public final class CustomerDto implements Serializable {
    private final Long id;
    private final String name;
    private final String address;

    @JsonCreator
    public CustomerDto(@JsonProperty("id") Long id,
                       @JsonProperty("name") String name,
                       @JsonProperty("address") String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}