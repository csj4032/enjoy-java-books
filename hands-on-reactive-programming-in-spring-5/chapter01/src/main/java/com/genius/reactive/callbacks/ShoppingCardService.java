package com.genius.reactive.callbacks;

import com.genius.reactive.commons.Input;
import com.genius.reactive.commons.Output;

import java.util.concurrent.Future;
import java.util.function.Consumer;

public interface ShoppingCardService {

	void calculate(Input input, Consumer<Output> consumer);

	Future<Output> calculate(Input input);
}
