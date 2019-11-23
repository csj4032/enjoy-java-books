package com.genius.reactive.callbacks;

import com.genius.reactive.commons.Input;
import com.genius.reactive.commons.Output;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Future;
import java.util.function.Consumer;

@Slf4j
public class AsyncShoppingCardService implements ShoppingCardService {

	@Override
	public void calculate(Input input, Consumer<Output> consumer) {
		Thread thread = new Thread(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			consumer.accept(new Output());
		});
		thread.start();
	}

	@Override
	public Future<Output> calculate(Input input) {
		return null;
	}
}