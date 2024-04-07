package com.sbmicroservices.orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.sbmicroservices.orderservice.dto.InventoryResponse;
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
			.map(this::mapToDto)
			.toList();

		order.setOrderLineItemsList(orderLineItems);
		for (OrderLineItems item : orderLineItems) {
			System.out.println(item); // Assuming OrderLineItemDto has overridden toString() method
		}

		List<String> skuCodes = order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();
		System.out.println(skuCodes);
		// Call InventoryService, and place the order if the product is in stock
		InventoryResponse[] inventoryResponseArray = webClient.get()
				.uri("http://inventory-service/api/inventory",
						uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
				.retrieve()
				.bodyToMono(InventoryResponse[].class)
				.block();

		boolean allProductsIsInStock = inventoryResponseArray.length > 0 && Arrays.stream(inventoryResponseArray)
				.allMatch(InventoryResponse::getIsInStock);

		if(allProductsIsInStock) {
			orderRepository.save(order);
		} else {
			throw new IllegalArgumentException("Product not in stock, please try again later.");
		}
	}
	
	private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
		OrderLineItems orderLineItems = new OrderLineItems();
		System.out.println(orderLineItemsDto.getSkuCode());
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
		return orderLineItems;
	}
}
