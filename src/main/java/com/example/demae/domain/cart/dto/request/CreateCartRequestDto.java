package com.example.demae.domain.cart.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateCartRequestDto {
	private int orderItemPrice;
	private int orderItemQuantity;
	private Long storeId;
	private Long menuId;
}
