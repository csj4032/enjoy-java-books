package com.genius.reactive.futures;

import com.genius.reactive.callbacks.ShoppingCardService;
import com.genius.reactive.commons.Input;
import com.genius.reactive.commons.Output;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.function.Consumer;

public class FutureShoppingCardService implements ShoppingCardService {

	@Override
	public void calculate(Input input, Consumer<Output> consumer) {
	}

	@Override
	public Future<Output> calculate(Input input) {
		FutureTask<Output> future = new FutureTask<>(() -> {
			Thread.sleep(1000);
			return new Output();
		});
		new Thread(future).start();
		return future;
	}
}
