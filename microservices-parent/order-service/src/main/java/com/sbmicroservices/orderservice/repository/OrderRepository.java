package com.sbmicroservices.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbmicroservices.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
