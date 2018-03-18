package com.skipthedishes.hackathon.productservice.controller;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.skipthedishes.hackathon.productservice.model.Product;

public final class ProductDto implements Serializable {
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

    public static ProductDto toDto(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getPrice());
    }
}
