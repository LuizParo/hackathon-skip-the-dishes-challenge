package com.skipthedishes.hackathon.orderservice.controller.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.skipthedishes.hackathon.orderservice.exception.InvalidOrderStatusException;
import com.skipthedishes.hackathon.orderservice.model.OrderCustomer;
import com.skipthedishes.hackathon.orderservice.model.OrderCustomerItem;
import com.skipthedishes.hackathon.orderservice.model.Status;

@JsonInclude(JsonInclude.Include.NON_NULL)
public final class OrderCustomerDto implements Serializable {
    private final Long id;
    private final String status;
    private final BigDecimal total;
    private final CustomerDto customer;
    private final Collection<OrderCustomerItemDto> items;

    @JsonCreator
    public OrderCustomerDto(@JsonProperty("id") Long id,
                            @JsonProperty("status") String status,
                            @JsonProperty("total") BigDecimal total,
                            @JsonProperty("customer") CustomerDto customer,
                            @JsonProperty("items") Collection<OrderCustomerItemDto> items) {
        this.id = id;
        this.status = status;
        this.total = total;
        this.customer = customer;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public Collection<OrderCustomerItemDto> getItems() {
        return items;
    }

    public static OrderCustomerDto toDto(OrderCustomer order, CustomerDto customer, Collection<OrderCustomerItemDto> items) {
        return new OrderCustomerDto(order.getId(), order.getStatus().toString(), order.getTotal(), customer, items);
    }

    public OrderCustomer fromDto() {
        List<OrderCustomerItem> items = this.items.stream()
                .map(item -> item.fromDto())
                .collect(Collectors.toList());

        Status status = Status.fromString(this.status).orElseThrow(() -> new InvalidOrderStatusException(String.format("status invalid: %s", this.status)));

        return new OrderCustomer(status, this.customer.getId(), items);
    }
}