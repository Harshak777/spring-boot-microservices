package com.sbmicroservices.orderservice.dto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
	private List<OrderLineItemsDto> orderLineItemsDto;
}
