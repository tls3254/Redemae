package com.example.demae.domain.cart.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CartListResponseDto {
	private Long cartId;
	private List<CartResponseDto> menu = new ArrayList<>();
	private int totalPrice;

	public CartListResponseDto(Long cartId) {
		this.cartId = cartId;
	}

	public void addItem(CartResponseDto itemDto) {
		this.menu.add(itemDto);
	}

	public void addToTotalPrice(int price) {
		this.totalPrice += price;
	}
}
