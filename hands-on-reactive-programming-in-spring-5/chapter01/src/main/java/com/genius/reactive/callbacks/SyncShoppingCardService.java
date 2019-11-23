package com.genius.reactive.callbacks;

import com.genius.reactive.commons.Input;
import com.genius.reactive.commons.Output;

import java.util.concurrent.Future;
import java.util.function.Consumer;

public class SyncShoppingCardService implements ShoppingCardService {

	@Override
	public void calculate(Input input, Consumer<Output> consumer) {
		consumer.accept(new Output());
	}

	@Override
	public Future<Output> calculate(Input input) {
		return null;
	}
}
