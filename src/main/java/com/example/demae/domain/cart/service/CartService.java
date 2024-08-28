package com.example.demae.domain.cart.service;

import com.example.demae.domain.cart.dto.request.CreateCartRequestDto;
import com.example.demae.domain.cart.dto.response.*;
import com.example.demae.domain.cart.entity.Cart;
import com.example.demae.domain.cart.entity.CartState;
import com.example.demae.domain.cart.entity.OrderItem;
import com.example.demae.domain.cart.repository.CartRepository;
import com.example.demae.domain.cart.repository.OrderItemRepository;
import com.example.demae.domain.menu.entity.Menu;
import com.example.demae.domain.menu.service.MenuService;
import com.example.demae.domain.store.entity.Store;
import com.example.demae.domain.store.service.StoreService;
import com.example.demae.domain.user.entity.User;
import com.example.demae.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

	private final CartRepository cartRepository;
	private final OrderItemRepository orderItemRepository;
	private final UserService userService;
	private final StoreService storeService;
	private final MenuService menuService;

	public void createCartAndOrderItem(CreateCartRequestDto orderRequestDto, String userEmail) {
		User user = userService.findUser(userEmail);

		Cart cart = cartRepository.findByUser_UserIdAndCartState(user.getUserId(), CartState.READY);

		if (cart == null) {
			cart = new Cart(user);
			cartRepository.save(cart);
		}

		Store store = storeService.findStore(orderRequestDto.getStoreId());
		Menu menu = menuService.findMenu(orderRequestDto.getMenuId());
		OrderItem orderItem = new OrderItem(orderRequestDto.getOrderItemPrice(), orderRequestDto.getOrderItemQuantity(), store, menu, cart);
		orderItemRepository.save(orderItem);

		cart.addTotalPrice(orderRequestDto.getOrderItemPrice() * orderRequestDto.getOrderItemQuantity());
	}

//	public void deleteOrderItem(Long cartId, DeleteCartRequestDto cartRequestDto, String userEmail) {
//		User user = userService.findUser(userEmail);
//
//		Cart cart = findCart(cartId);
//
//		OrderItem orderItem = orderItemRepository.findByCart_CartIdAndOrderItemId(cartId, cartRequestDto.getOrderItemId());
//
////		Cart cart = cartRepository.findByUser_UserIdAndCartState(user.getUserId(), CartState.READY);
////		CartState cartState = CartState.valueOf(cartRequestDto.getCartState().toUpperCase());
////		cart.updateCartState(cartState);
//	}

	public void deleteCart(Long cartId, String userEmail) {
		Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new IllegalArgumentException("장바구니가 없습니다."));
		if(!userEmail.equals(cart.getUser().getUserEmail())){
			throw new IllegalArgumentException("본인의 장바구니가 아닙니다.");
		}
		cartRepository.delete(cart);
	}

	@Transactional(readOnly = true)
	public List<CartAllResponseDto> getAllCartInfo(String userEmail) {
		User user = userService.findUser(userEmail);
		List<Cart> orderList = cartRepository.findByUser_UserId(user.getUserId());
		return orderList.stream().map(CartAllResponseDto::new).toList();
	}

	@Transactional(readOnly = true)
	public OrderResponseDto getCart(Long cartId, String userEmail) {
		User user = userService.findUser(userEmail);
		Cart cart = findCart(cartId);
//		if (!user.getStore().getStoreId().equals(cart.getOrderItems().get(0).getStore().getStoreId())) {
//			throw new IllegalStateException("본인 리뷰만 조회가 가능합니다.");
//		}
		List<OrderItem> orderItems = orderItemRepository.findByCart_CartId(cartId);

		OrderResponseDto orderResponseDto = new OrderResponseDto();
		for (OrderItem orderItem : orderItems) {
			OrderItemResponseDto orderItem1 = new OrderItemResponseDto(orderItem);
			orderResponseDto.addItem(orderItem1);
			orderResponseDto.addToTotalPrice(orderItem.getPrice() * orderItem.getQuantity());
		}
		return orderResponseDto;
	}


	@Transactional(readOnly = true)
	public CartListResponseDto getCarts(Long userId) {
		Cart cart = cartRepository.findByUser_UserIdAndCartState(userId, CartState.READY);
		List<OrderItem> orderItems = cart.getOrderItems();
		CartListResponseDto cartResponseDto = new CartListResponseDto(cart.getCartId());
		Long userIdFromCart = cart.getUser().getUserId();

		for (OrderItem orderItem : orderItems) {
			Menu menu = orderItem.getMenu();
			CartResponseDto menuDto = new CartResponseDto(menu, orderItem.getPrice(), orderItem.getQuantity(), userIdFromCart);
			cartResponseDto.addItem(menuDto);
			cartResponseDto.addToTotalPrice(orderItem.getPrice() * orderItem.getQuantity());
		}
		return cartResponseDto;
	}

	@Transactional(readOnly = true)
	public Cart findCart(Long cartId){
		return cartRepository.findById(cartId).orElseThrow(() -> new IllegalArgumentException("카트가 없습니다."));
	}

	public Cart getOrderForUser(Long cartId, String userEmail) {
		User user = userService.findUser(userEmail);
		Cart findOrder = cartRepository.findById(cartId).orElseThrow();
		if (findOrder.getCartId().equals(cartId)  && findOrder.getUser().getUserId().equals(user.getUserId()))  {
			return findOrder;
		}
		throw new IllegalStateException("본인 주문 정보만 조회가 가능합니다.");
	}

	public Cart completeOrder(Long cartId, String userEmail) {
		User user = userService.findUser(userEmail);
		Cart findOrder = cartRepository.findById(cartId).orElseThrow();
		if (user.getStore() != null && user.getStore().getStoreId().equals(findOrder.getOrderItems().get(0).getStore().getStoreId()))  {
			return cartRepository.findById(cartId).orElseThrow();
		}
		return null;
	}
}
