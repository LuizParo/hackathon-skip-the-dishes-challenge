package com.skipthedishes.hackathon.orderservice.controller.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {
    private final Long id;
    private final String name;
    private final BigDecimal price;

    @JsonCreator
    public ProductDto(@JsonProperty("id") Long id,
                      @JsonProperty("name") String name,
                      @JsonProperty("price") BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}