package com.skipthedishes.hackathon.productservice.controller;

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

import com.skipthedishes.hackathon.productservice.model.Product;
import com.skipthedishes.hackathon.productservice.service.ProductService;

@RestController
@RequestMapping(value = "/products", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {
    private final ProductService productService;
    private final ProductToDtoConverter productToDtoConverter;
    private final ProductListToDtoListConverter productListToDtoListConverter;

    @Autowired
    public ProductController(ProductService productService, ProductToDtoConverter productToDtoConverter, ProductListToDtoListConverter productListToDtoListConverter) {
        this.productService = productService;
        this.productToDtoConverter = productToDtoConverter;
        this.productListToDtoListConverter = productListToDtoListConverter;
    }

    @GetMapping(consumes = "*/*")
    public Iterable<ProductDto> findAll() {
        return this.productListToDtoListConverter.toDto(this.productService.findAllProducts());
    }

    @GetMapping(path = "/{id}", consumes = "*/*")
    public ProductDto findById(@PathVariable Long id) {
        Product product = this.productService.findById(id);
        return this.productToDtoConverter.toDto(product);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ProductDto productDto) {
        Product product = this.productToDtoConverter.fromDto(productDto);
        Product savedProduct = this.productService.save(product);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProduct.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
