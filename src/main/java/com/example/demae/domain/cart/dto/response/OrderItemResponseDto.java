package com.example.demae.domain.cart.dto.response;

import com.example.demae.domain.cart.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemResponseDto {
	private int price;
	private int quantity;
	private Long cartId;

	public OrderItemResponseDto(OrderItem orderList) {
		this.price = orderList.getPrice();
		this.quantity = orderList.getQuantity();
		this.cartId = orderList.getCart().getCartId();
	}
}
