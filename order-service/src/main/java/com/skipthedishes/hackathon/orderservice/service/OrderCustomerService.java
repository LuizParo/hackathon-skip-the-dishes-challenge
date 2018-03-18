package com.skipthedishes.hackathon.orderservice.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skipthedishes.hackathon.orderservice.controller.dto.OrderCustomerDto;
import com.skipthedishes.hackathon.orderservice.controller.OrderCustomerToDtoConverter;
import com.skipthedishes.hackathon.orderservice.exception.InvalidOrderStatusException;
import com.skipthedishes.hackathon.orderservice.exception.OrderNotFoundException;
import com.skipthedishes.hackathon.orderservice.model.OrderCustomer;
import com.skipthedishes.hackathon.orderservice.model.Status;
import com.skipthedishes.hackathon.orderservice.repository.OrderCustomerRepository;

@Service
public class OrderCustomerService {
    private final OrderCustomerRepository orderCustomerRepository;
    private final OrderCustomerToDtoConverter converter;

    @Autowired
    public OrderCustomerService(OrderCustomerRepository orderCustomerRepositor, OrderCustomerToDtoConverter converter) {
        this.orderCustomerRepository = orderCustomerRepositor;
        this.converter = converter;
    }

    public Iterable<OrderCustomerDto> findAll() {
        return this.orderCustomerRepository.findAll().stream()
            .map(this.converter::toDto)
            .collect(Collectors.toList());
    }

    public OrderCustomerDto findById(Long id) {
        OrderCustomer order = this.orderCustomerRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(String.format("order %d not found", id)));
        return this.converter.toDto(order);
    }

    public OrderCustomer save(OrderCustomerDto orderDto) {
        OrderCustomer order = this.converter.fromDto(orderDto);
        return this.orderCustomerRepository.save(order);
    }

    public void updateStatus(Long id, String status) {
        Status newStatus = Status.fromString(status).orElseThrow(() -> new InvalidOrderStatusException(String.format("status invalid: %s", status)));

        OrderCustomer order = this.orderCustomerRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(String.format("order %s not found", id)));
        order.setStatus(newStatus);

        this.orderCustomerRepository.save(order);
    }
}