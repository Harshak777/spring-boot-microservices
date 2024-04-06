package com.sbmicroservices.orderservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbmicroservices.orderservice.dto.OrderLineItemsDto;
import com.sbmicroservices.orderservice.dto.OrderRequest;
import com.sbmicroservices.orderservice.model.Order;
import com.sbmicroservices.orderservice.model.OrderLineItems;
import com.sbmicroservices.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
	
	private final OrderRepository orderRepository;
	private final WebClient webClient;
	
	public void placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		
		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
			.stream()
			.map(orderLineItemsDto -> mapToDto(orderLineItemsDto))
			.toList();

		order.setOrderLineItemsList(orderLineItems);

		// Call InventoryService, and place the order if the product is in stock
		Boolean result = webClient.get()
				.uri("http://localhost:8082/api/inventory")
				.retrieve()
				.bodyToMono(Boolean.class)
				.block();

		if(Boolean.TRUE.equals(result)) {
			orderRepository.save(order);
		} else {
			throw new IllegalArgumentException("Product not in stock, please try again later.");
		}
	}
	
	private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setPrice(orderLineItems.getPrice());
		orderLineItems.setQuantity(orderLineItems.getQuantity());
		orderLineItems.setSkuCode(orderLineItems.getSkuCode());
		return orderLineItems;
	}
}
