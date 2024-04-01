package com.sbmicroservies.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sbmicroservies.productservice.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
