package com.skipthedishes.hackathon.productservice.controller;

import org.springframework.stereotype.Component;

import com.skipthedishes.hackathon.productservice.model.Product;

@Component
public class ProductToDtoConverter implements Converter<Product, ProductDto> {

    @Override
    public Product fromDto(ProductDto dto) {
        return new Product(dto.getId(), dto.getName(), dto.getPrice());
    }

    @Override
    public ProductDto toDto(Product product) {
        return ProductDto.toDto(product);
    }
}
