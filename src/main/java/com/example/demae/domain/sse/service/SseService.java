package com.example.demae.domain.sse.service;

import com.example.demae.domain.cart.entity.Cart;
import com.example.demae.domain.user.entity.User;
import com.example.demae.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class SseService {
	private static final long DEFAULT_TIMEOUT = 30 * 60 * 1000;
	private final Map<String, SseEmitter> userEmitters = new ConcurrentHashMap<>();
	private final UserService userService;

	public SseEmitter createConnect(String username) {
		User user = userService.findUser(username);
		String userId = String.valueOf(user.getUserId());

		if(userEmitters.containsKey(String.valueOf(userId))) {
			userEmitters.remove(userId);
		}

		SseEmitter emitter = new SseEmitter(DEFAULT_TIMEOUT);
		userEmitters.put(userId, emitter);
		emitter.onCompletion(() -> userEmitters.remove(userId, emitter));
		emitter.onTimeout(() -> userEmitters.remove(userId, emitter));

		return emitter;
	}

	public SseEmitter getUserEmitters(String userId) {
		return userEmitters.get(String.valueOf(userId));
	}

	public void deleteEmitters(SseEmitter emitter) {
		userEmitters.remove(emitter);
	}

	public List<SseEmitter> findUserAndStore(Cart cart){
		String userId = String.valueOf(cart.getUser().getUserId());
		String storeUserId = String.valueOf(cart.getOrderItems().get(0).getStore().getUser().getUserId());
		SseEmitter userEmitter = getUserEmitters(userId);
		SseEmitter storeEmitter = getUserEmitters(storeUserId);
		return Arrays.asList(userEmitter, storeEmitter);
	}
}
