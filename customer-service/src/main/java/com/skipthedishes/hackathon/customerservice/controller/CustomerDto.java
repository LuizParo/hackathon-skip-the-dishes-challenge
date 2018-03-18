package com.skipthedishes.hackathon.customerservice.controller;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.skipthedishes.hackathon.customerservice.model.Customer;

public final class CustomerDto {
    private final Long id;
    private final String name;
    private final String address;
    private final Date creation;

    @JsonCreator
    public CustomerDto(@JsonProperty("id") Long id,
                       @JsonProperty("name") String name,
                       @JsonProperty("address") String address,
                       @JsonProperty("creation") Date creation) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.creation = creation;
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

    public Date getCreation() {
        return creation;
    }

    public static CustomerDto toDto(Customer customer) {
        return new CustomerDto(customer.getId(), customer.getName(), customer.getAddress(), customer.getCreation());
    }
}
