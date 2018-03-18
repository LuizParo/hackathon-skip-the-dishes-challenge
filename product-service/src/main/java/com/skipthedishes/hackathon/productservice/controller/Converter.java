package com.skipthedishes.hackathon.productservice.controller;

public interface Converter<T, U> {
    T fromDto(U dto);

    U toDto(T entity);
}
