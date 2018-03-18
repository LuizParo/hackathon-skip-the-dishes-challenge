package com.skipthedishes.hackathon.customerservice.controller;

public interface Converter<T, U> {

    U toDto(T entity);

    T fromDto(U dto);
}
