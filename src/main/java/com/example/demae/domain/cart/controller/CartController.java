package com.example.demae.domain.cart.controller;

import com.example.demae.domain.cart.dto.request.DeleteCartRequestDto;
import com.example.demae.domain.cart.dto.request.CreateCartRequestDto;
import com.example.demae.domain.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/carts")
public class CartController {

	private final CartService cartService;

	@PostMapping
	public ResponseEntity<String> createCartAndOrderItem(
												@RequestBody CreateCartRequestDto orderRequestDto,
										        @AuthenticationPrincipal UserDetails userDetails) {
		cartService.createCartAndOrderItem(orderRequestDto, userDetails.getUsername());
		return ResponseEntity.ok("ok");
	}

//	@DeleteMapping("/value/{cartId}")
//	public ResponseEntity<String> deleteOrderItem(@PathVariable Long cartId,
//												 @RequestBody DeleteCartRequestDto cartRequestDto,
//										         @AuthenticationPrincipal UserDetails userDetails) {
//		cartService.deleteOrderItem(cartId, cartRequestDto, userDetails.getUsername());
//		return ResponseEntity.ok("ok");
//	}

	@DeleteMapping("/{cartId}")
	public ResponseEntity<String> deleteCart(@PathVariable Long cartId,
											 @AuthenticationPrincipal UserDetails userDetails) {
		cartService.deleteCart(cartId, userDetails.getUsername());
		return ResponseEntity.ok("ok");
	}
}