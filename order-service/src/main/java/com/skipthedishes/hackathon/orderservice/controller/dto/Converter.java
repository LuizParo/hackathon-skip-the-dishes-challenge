package com.skipthedishes.hackathon.orderservice.controller.dto;

public interface Converter<T, U> {
     U toDto(T entity);

     T fromDto(U dto);
}
