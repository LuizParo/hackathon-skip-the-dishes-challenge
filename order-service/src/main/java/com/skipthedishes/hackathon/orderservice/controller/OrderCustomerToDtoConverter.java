package com.skipthedishes.hackathon.orderservice.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skipthedishes.hackathon.orderservice.controller.dto.Converter;
import com.skipthedishes.hackathon.orderservice.controller.dto.CustomerDto;
import com.skipthedishes.hackathon.orderservice.controller.dto.OrderCustomerDto;
import com.skipthedishes.hackathon.orderservice.controller.dto.OrderCustomerItemDto;
import com.skipthedishes.hackathon.orderservice.controller.dto.ProductDto;
import com.skipthedishes.hackathon.orderservice.model.OrderCustomer;
import com.skipthedishes.hackathon.orderservice.proxy.CustomerServiceProxy;
import com.skipthedishes.hackathon.orderservice.proxy.ProductServiceProxy;

@Component
public class OrderCustomerToDtoConverter implements Converter<OrderCustomer, OrderCustomerDto> {
    private final CustomerServiceProxy customerServiceProxy;
    private final ProductServiceProxy productServiceProxy;

    @Autowired
    public OrderCustomerToDtoConverter(CustomerServiceProxy customerServiceProxy, ProductServiceProxy productServiceProxy) {
        this.customerServiceProxy = customerServiceProxy;
        this.productServiceProxy = productServiceProxy;
    }

    public OrderCustomerDto toDto(OrderCustomer order) {
        CustomerDto customerDto = this.customerServiceProxy.retrieveCustomer(order.getCustomerId());

        Collection<OrderCustomerItemDto> itemsDto = order.getItems().stream()
                .map(item -> {
                    ProductDto productDto = this.productServiceProxy.retrieveProduct(item.getProductId());
                    return OrderCustomerItemDto.toDto(item, productDto);
                })
                .collect(Collectors.toList());


        return OrderCustomerDto.toDto(order, customerDto, itemsDto);
    }

    public OrderCustomer fromDto(OrderCustomerDto dto) {
        return dto.fromDto();
    }
}
