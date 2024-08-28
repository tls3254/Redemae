package com.example.demae.domain.cart.dto.response;

import com.example.demae.domain.cart.entity.Cart;
import com.example.demae.domain.cart.entity.CartState;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CartAllResponseDto {
	private Long cartId;
	private Long userId;
	private LocalDateTime date;
	private CartState state;
	private int totalPrice;
	private String storeName;

	public CartAllResponseDto(Cart cart) {
		this.cartId = cart.getCartId();
		this.userId = cart.getUser().getUserId();
		this.date = cart.getModifiedAt();
		this.state = cart.getCartState();
		this.totalPrice = cart.getTotalPrice();
		this.storeName = cart.getOrderItems().get(0).getStore().getStoreName();
	}
}
