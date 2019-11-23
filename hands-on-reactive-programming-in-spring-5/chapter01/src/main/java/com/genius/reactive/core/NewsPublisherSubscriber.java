package com.genius.reactive.core;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Iterator;
import java.util.List;

@Slf4j
public class NewsPublisherSubscriber {

	public static void main(String[] args) {
		NewsSubscriber subscriber = new NewsSubscriber();
		NewsPublisher publisher = new NewsPublisher();
		publisher.subscribe(subscriber);
	}

	static class NewsPublisher implements Publisher<Integer> {

		Iterator iterator = List.of(1, 2, 3, 4, 5).iterator();

		@Override
		public void subscribe(Subscriber subscriber) {
			log.info("subscribe");
			subscriber.onSubscribe(new Subscription() {
				@Override
				public void request(long n) {
					log.info("request : {}", n);
					int i = 0;
					while (i++ < n) {
						if (iterator.hasNext()) {
							subscriber.onNext(iterator.next());
						} else {
							subscriber.onComplete();
							break;
						}
					}
				}

				@Override
				public void cancel() {

				}
			});
		}
	}

	static class NewsSubscriber implements Subscriber<Integer> {

		Subscription subscription;

		@Override
		public void onSubscribe(Subscription subscription) {
			log.info("onSubscribe");
			this.subscription = subscription;
			subscription.request(1);
		}

		@Override
		public void onNext(Integer i) {
			log.info("onNext : {}", i);
			subscription.request(1);
		}

		@Override
		public void onError(Throwable throwable) {
			log.info("onError : {}", throwable);
		}

		@Override
		public void onComplete() {
			log.info("onComplete");
		}
	}
}
