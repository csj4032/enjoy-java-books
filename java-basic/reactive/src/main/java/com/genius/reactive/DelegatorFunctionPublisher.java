package com.genius.reactive;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Flow.Publisher;
import java.util.function.Function;

import static java.util.concurrent.Flow.Subscriber;

@Slf4j
public class DelegatorFunctionPublisher<T, R> implements Publisher<R> {

	private final GeniusPublisher<T> publisher;
	private final Function<T, R> function;

	public DelegatorFunctionPublisher(GeniusPublisher<T> publisher, Function<T, R> function) {
		this.publisher = publisher;
		this.function = function;
	}

	@Override
	public void subscribe(Subscriber<? super R> subscriber) {
		publisher.subscribe(new DelegatorSubscriber<T, R>(subscriber) {
			@Override
			public void onNext(T message) {
				subscriber.onNext(function.apply(message));
			}
		});
	}
}