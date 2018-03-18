package com.skipthedishes.hackathon.orderservice.controller.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.skipthedishes.hackathon.orderservice.model.OrderCustomerItem;

@JsonInclude(JsonInclude.Include.NON_NULL)
public final class OrderCustomerItemDto {
    private final Long id;
    private final ProductDto product;
    private final BigDecimal quantity;
    private final BigDecimal price;

    @JsonCreator
    public OrderCustomerItemDto(@JsonProperty("id") Long id,
                                @JsonProperty("product") ProductDto product,
                                @JsonProperty("quantity") BigDecimal quantity,
                                @JsonProperty("price") BigDecimal price) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public ProductDto getProduct() {
        return product;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public static OrderCustomerItemDto toDto(OrderCustomerItem item, ProductDto product) {
        return new OrderCustomerItemDto(item.getId(), product, item.getQuantity(), item.getPrice());
    }

    public OrderCustomerItem fromDto() {
        return new OrderCustomerItem(this.id, this.product.getId(), this.quantity, this.price);
    }
}