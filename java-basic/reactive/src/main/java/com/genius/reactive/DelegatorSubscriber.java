package com.genius.reactive;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Flow.Subscriber;

import static java.util.concurrent.Flow.Subscription;

@Slf4j
public abstract class DelegatorSubscriber<T, R> implements Subscriber<T> {

	private final Subscriber<? super R> subscriber;

	public DelegatorSubscriber(Subscriber<? super R> subscriber) {
		this.subscriber = subscriber;
	}

	@Override
	public void onSubscribe(Subscription subscription) {
		subscriber.onSubscribe(subscription);
	}

	@Override
	public abstract void onNext(T message);

	@Override
	public void onError(Throwable throwable) {
		subscriber.onError(throwable);
	}

	@Override
	public void onComplete() {
		subscriber.onComplete();
	}
}
