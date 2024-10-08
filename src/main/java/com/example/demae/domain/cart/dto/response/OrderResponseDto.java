package com.example.demae.domain.cart.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderResponseDto {
	private List<OrderItemResponseDto> orderList = new ArrayList<>();
	private int totalPrice;

	public void addItem(OrderItemResponseDto orderDto) {
		this.orderList.add(orderDto);
	}

	public void addToTotalPrice(int price) {
		this.totalPrice += price;
	}
}
