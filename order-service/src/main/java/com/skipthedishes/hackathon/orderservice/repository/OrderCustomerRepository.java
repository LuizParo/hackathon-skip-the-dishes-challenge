package com.skipthedishes.hackathon.orderservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skipthedishes.hackathon.orderservice.model.OrderCustomer;

public interface OrderCustomerRepository extends JpaRepository<OrderCustomer, Long> {

    @Override
    @Query("select o from OrderCustomer o left join fetch o.items where o.id = :id")
    Optional<OrderCustomer> findById(@Param("id") Long id);
}
