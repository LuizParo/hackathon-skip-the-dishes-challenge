package com.skipthedishes.hackathon.orderservice.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_CUSTOMER")
public class OrderCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Status status;
    private Long customerId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Collection<OrderCustomerItem> items = new ArrayList<>();

    OrderCustomer() {}

    public OrderCustomer(Status status, Long customerId, Collection<OrderCustomerItem> items) {
        this.status = status;
        this.customerId = customerId;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return this.items.stream()
            .map(OrderCustomerItem::getPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Collection<OrderCustomerItem> getItems() {
        return items;
    }

    public void setItems(Collection<OrderCustomerItem> items) {
        this.items = items;
    }
}
