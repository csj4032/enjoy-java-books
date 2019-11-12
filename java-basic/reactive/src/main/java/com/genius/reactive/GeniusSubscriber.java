package com.genius.reactive;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Flow.Subscriber;

import static java.util.concurrent.Flow.Subscription;

@Slf4j
public class GeniusSubscriber<T> implements Subscriber<T> {

	@Override
	public void onSubscribe(Subscription subscription) {
		log.debug("onSubscribe");
		subscription.request(Long.MAX_VALUE);
	}

	@Override
	public void onNext(T message) {
		log.debug("onNext:{}", message);
	}

	@Override
	public void onError(Throwable throwable) {
		log.debug("onError {}", throwable.getCause());
	}

	@Override
	public void onComplete() {
		log.debug("onComplete");
	}
}
