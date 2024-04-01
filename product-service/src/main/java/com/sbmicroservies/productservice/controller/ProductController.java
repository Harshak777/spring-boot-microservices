package com.sbmicroservies.productservice.controller;

import org.springframework.web.bind.annotation.*;

import com.sbmicroservies.productservice.dto.ProductRequest;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createProduct(@RequestBody ProductRequest productRequest) {
		
	}
}
