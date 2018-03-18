package com.skipthedishes.hackathon.productservice.controller;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Component;

import com.skipthedishes.hackathon.productservice.model.Product;

@Component
public class ProductListToDtoListConverter implements Converter<Iterable<Product>, Iterable<ProductDto>> {

    @Override
    public Iterable<Product> fromDto(Iterable<ProductDto> dtos) {
        return StreamSupport.stream(dtos.spliterator(), false)
                .map(dto -> new Product(dto.getId(), dto.getName(), dto.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<ProductDto> toDto(Iterable<Product> products) {
        return StreamSupport.stream(products.spliterator(), false)
                .map(ProductDto::toDto)
                .collect(Collectors.toList());
    }
}