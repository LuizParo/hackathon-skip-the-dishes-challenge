package com.skipthedishes.hackathon.customerservice.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.skipthedishes.hackathon.customerservice.model.Customer;
import com.skipthedishes.hackathon.customerservice.service.CustomerService;

@RestController
@RequestMapping(path = "/customers", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerToDtoConverter customerToDtoConverter;
    private final CustomerListToDtoListConverter customerListToDtoListConverter;

    @Autowired
    public CustomerController(CustomerService customerService, CustomerToDtoConverter customerToDtoConverter, CustomerListToDtoListConverter customerListToDtoListConverter) {
        this.customerService = customerService;
        this.customerToDtoConverter = customerToDtoConverter;
        this.customerListToDtoListConverter = customerListToDtoListConverter;
    }

    @GetMapping(consumes = "*/*")
    public Iterable<CustomerDto> findAll() {
        Iterable<Customer> customers = this.customerService.findAll();
        return this.customerListToDtoListConverter.toDto(customers);
    }

    @GetMapping(path = "/{id}", consumes = "*/*")
    public CustomerDto findById(@PathVariable Long id) {
        Customer customer = this.customerService.findById(id);
        return this.customerToDtoConverter.toDto(customer);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CustomerDto dto) {
        Customer customer = this.customerToDtoConverter.fromDto(dto);
        Customer savedCustomer = this.customerService.save(customer);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCustomer.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
