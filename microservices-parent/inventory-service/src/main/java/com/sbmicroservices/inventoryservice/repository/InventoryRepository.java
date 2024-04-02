package com.sbmicroservices.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbmicroservices.inventoryservice.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
