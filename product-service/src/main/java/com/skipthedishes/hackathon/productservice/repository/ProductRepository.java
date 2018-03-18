package com.skipthedishes.hackathon.productservice.repository;

import org.springframework.data.repository.Repository;

import com.skipthedishes.hackathon.productservice.model.Product;

public interface ProductRepository extends Repository<Product, Long> {

    Iterable<Product> findAll();

    Product save(Product product);

    Product findById(Long id);
}
