package com.genius.duality;

import java.util.concurrent.Flow;

public class Subscriber implements Flow.Subscriber<Integer> {

	Flow.Subscription subscription;

	@Override
	public void onSubscribe(Flow.Subscription subscription) {
		System.out.println("onSubscribe");
		this.subscription = subscription;
		this.subscription.request(1);
	}

	@Override
	public void onNext(Integer item) {
		System.out.println(Thread.currentThread().getName() + " onNext : " + item);
		this.subscription.request(1);
	}

	@Override
	public void onError(Throwable throwable) {

	}

	@Override
	public void onComplete() {
		System.out.println("onComplete");
	}
}
