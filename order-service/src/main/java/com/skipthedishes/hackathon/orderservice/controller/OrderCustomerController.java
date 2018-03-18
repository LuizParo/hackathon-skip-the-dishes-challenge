package com.skipthedishes.hackathon.orderservice.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.skipthedishes.hackathon.orderservice.controller.dto.OrderCustomerDto;
import com.skipthedishes.hackathon.orderservice.controller.dto.StatusDto;
import com.skipthedishes.hackathon.orderservice.model.OrderCustomer;
import com.skipthedishes.hackathon.orderservice.service.OrderCustomerService;

@RestController
@RequestMapping(path = "/orders", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class OrderCustomerController {
    private final OrderCustomerService orderCustomerService;

    @Autowired
    public OrderCustomerController(OrderCustomerService orderCustomerService) {
        this.orderCustomerService = orderCustomerService;
    }

    @GetMapping(consumes = "*/*")
    public Iterable<OrderCustomerDto> findAll() {
        return this.orderCustomerService.findAll();
    }

    @GetMapping(path = "/{id}", consumes = "*/*")
    public OrderCustomerDto findById(@PathVariable("id") Long id) {
        return this.orderCustomerService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody OrderCustomerDto dto) {
        OrderCustomer order = this.orderCustomerService.save(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(order.getId())
            .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody StatusDto status) {
        this.orderCustomerService.updateStatus(id, status.getStatus());

        return ResponseEntity.noContent().build();
    }
}