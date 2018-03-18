package com.skipthedishes.hackathon.orderservice.controller.dto;

import java.io.Serializable;

import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public final class StatusDto implements Serializable {
    private final String status;

    @JsonCreator
    public StatusDto(@PathVariable String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}