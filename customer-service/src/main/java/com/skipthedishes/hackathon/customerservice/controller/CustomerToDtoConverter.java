package com.skipthedishes.hackathon.customerservice.controller;

import org.springframework.stereotype.Component;

import com.skipthedishes.hackathon.customerservice.model.Customer;

@Component
public class CustomerToDtoConverter implements Converter<Customer, CustomerDto> {

    @Override
    public CustomerDto toDto(Customer customer) {
        return CustomerDto.toDto(customer);
    }

    @Override
    public Customer fromDto(CustomerDto dto) {
        return new Customer(dto.getId(), dto.getName(), dto.getAddress(), dto.getCreation(), "");
    }
}
