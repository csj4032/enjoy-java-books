package com.genius.reactive;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Flow.Publisher;
import java.util.function.Function;

import static java.util.concurrent.Flow.Subscriber;
import static java.util.concurrent.Flow.Subscription;

@Slf4j
public class GeniusPublisher<T> implements Publisher<T> {

	private final Iterable<T> iterable;

	public GeniusPublisher(Iterable<T> iterable) {
		this.iterable = iterable;
	}

	@Override
	public void subscribe(Subscriber<? super T> subscriber) {
		log.debug("subscribe");
		subscriber.onSubscribe(new Subscription() {
			@Override
			public void request(long n) {
				log.debug("request");
				try {
					iterable.forEach(m -> subscriber.onNext(m));
					subscriber.onComplete();
				} catch (Exception e) {
					subscriber.onError(e);
				}
			}

			@Override
			public void cancel() {}
		});
	}

	public static void main(String[] args) {
		GeniusPublisher<Message> publish = new GeniusPublisher(List.of(new Message(1l, "message"), new Message(2l, "message"), new Message(3l, "message"), new Message(4l, "message")));
		DelegatorFunctionPublisher<Message, MessageExtend> messageCreatePublisher = new DelegatorFunctionPublisher(publish, (Function<Message, MessageExtend>) m -> new MessageExtend(m.getId(), m.getText(), LocalDateTime.now()));
		messageCreatePublisher.subscribe(new GeniusSubscriber<MessageExtend>());
	}
}