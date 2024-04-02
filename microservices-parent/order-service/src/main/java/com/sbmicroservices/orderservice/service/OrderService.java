package com.sbmicroservices.orderservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.sbmicroservices.orderservice.dto.OrderLineItemsDto;
import com.sbmicroservices.orderservice.dto.OrderRequest;
import com.sbmicroservices.orderservice.model.Order;
import com.sbmicroservices.orderservice.model.OrderLineItems;

@Service
public class OrderService {
	
	public void placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		
		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDto()
			.stream()
			.map(orderLineItemsDto -> mapToDto(orderLineItemsDto))
			.toList();
		
		order.setOrderLineItemsList(orderLineItems);
	}
	
	private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setPrice(orderLineItems.getPrice());
		orderLineItems.setQuantity(orderLineItems.getQuantity());
		orderLineItems.setSkuCode(orderLineItems.getSkuCode());
		return orderLineItems;
	}
}
