package com.genius.reactive.callbacks;

import com.genius.reactive.commons.Input;
import com.genius.reactive.commons.Output;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
public class OrdersService {

	private final ShoppingCardService shoppingCardService;

	public OrdersService(ShoppingCardService shoppingCardService) {
		this.shoppingCardService = shoppingCardService;
	}

	public void process() {
		Input input = new Input();
		shoppingCardService.calculate(input, output -> {
			log.info("{}, execution completed", shoppingCardService.getClass().getCanonicalName());
		});
	}

	public void processFuture() {
		Input input = new Input();
		Future<Output> result = shoppingCardService.calculate(input);
		log.info("future execution competed");
		try {
			result.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}