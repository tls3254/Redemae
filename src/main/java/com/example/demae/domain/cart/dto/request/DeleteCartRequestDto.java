package com.example.demae.domain.cart.dto.request;

import com.example.demae.domain.cart.entity.CartState;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteCartRequestDto {
	private String cartState;
}
