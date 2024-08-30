package com.example.demae.domain.cart.controller;

import com.example.demae.domain.cart.dto.response.CartListResponseDto;
import com.example.demae.domain.cart.dto.response.CartAllResponseDto;
import com.example.demae.domain.cart.dto.response.OrderResponseDto;
import com.example.demae.domain.user.entity.User;
import com.example.demae.domain.user.entity.UserRoleEnum;
import com.example.demae.domain.user.service.UserService;
import com.example.demae.domain.cart.service.CartService;
import com.example.demae.domain.sse.service.SseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartControllerPage {
	private final CartService cartService;
	private final UserService userService;
	private final SseService sseService;

	@GetMapping
	public String getAllCart(@AuthenticationPrincipal UserDetails userDetails,
							  Model model) {
		if(userDetails == null){
			return "root/error";
		}
		User user = userService.findUser(userDetails.getUsername());
		List<CartAllResponseDto> cart;
		if(user.getUserRole() == UserRoleEnum.ADMIN){
			cart = cartService.getAllCart(user.getStore().getStoreId());
		}
		else{
			cart = cartService.getAllCartInfo(userDetails.getUsername());
		}
		model.addAttribute("cart", cart);
		model.addAttribute("user", user);
		return "cart/orderAllInfoPage";
	}

	@GetMapping("/orderItem")
	public String getCart(@AuthenticationPrincipal UserDetails userDetails,
						   Model model) {
		if(userDetails == null){
			return "root/error";
		}
		User user = userService.findUser(userDetails.getUsername());
		CartListResponseDto cart = cartService.getCarts(user.getUserId());
		model.addAttribute("cartList", cart);
		return "cart/cart";
	}

	@GetMapping("/{cartId}")
	public String getOrder(@PathVariable Long cartId,
						   @AuthenticationPrincipal UserDetails userDetails,
						   Model model) {
		if(userDetails == null){
			return "root/error";
		}
		OrderResponseDto cart = cartService.getCart(cartId, userDetails.getUsername());
		model.addAttribute("cart", cart);
		return "cart/orderInfoPage";
	}
}
