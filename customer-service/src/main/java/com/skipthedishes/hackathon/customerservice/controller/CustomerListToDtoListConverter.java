package com.skipthedishes.hackathon.customerservice.controller;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Component;

import com.skipthedishes.hackathon.customerservice.model.Customer;

@Component
public class CustomerListToDtoListConverter implements Converter<Iterable<Customer>, Iterable<CustomerDto>> {
    @Override
    public Iterable<CustomerDto> toDto(Iterable<Customer> customers) {
        return StreamSupport.stream(customers.spliterator(), false)
                .map(CustomerDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Customer> fromDto(Iterable<CustomerDto> dtos) {
        return StreamSupport.stream(dtos.spliterator(), false)
                .map(dto -> new Customer(dto.getId(), dto.getName(), dto.getAddress(), dto.getCreation(), ""))
                .collect(Collectors.toList());
    }
}
