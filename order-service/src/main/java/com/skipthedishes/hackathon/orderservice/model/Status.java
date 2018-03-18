package com.skipthedishes.hackathon.orderservice.model;

import java.util.Optional;
public enum Status {
    OPENED,
    CLOSED,
    CANCELED;

    public static Optional<Status> fromString(String status) {
        return status != null
                ? Optional.ofNullable(Status.valueOf(status.toUpperCase()))
                : Optional.empty();
    }
}