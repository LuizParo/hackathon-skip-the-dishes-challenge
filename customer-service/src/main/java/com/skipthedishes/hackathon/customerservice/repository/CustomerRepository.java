package com.skipthedishes.hackathon.customerservice.repository;

import org.springframework.data.repository.Repository;

import com.skipthedishes.hackathon.customerservice.model.Customer;

public interface CustomerRepository extends Repository<Customer, Long> {

    Iterable<Customer> findAll();

    Customer save(Customer product);

    Customer findById(Long id);
}
