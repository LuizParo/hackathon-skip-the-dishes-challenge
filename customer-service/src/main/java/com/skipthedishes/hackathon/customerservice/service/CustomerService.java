package com.skipthedishes.hackathon.customerservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skipthedishes.hackathon.customerservice.model.Customer;
import com.skipthedishes.hackathon.customerservice.repository.CustomerRepository;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer save(Customer product) {
        return customerRepository.save(product);
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id);
    }
}
