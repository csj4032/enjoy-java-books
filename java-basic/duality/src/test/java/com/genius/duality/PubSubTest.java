package com.genius.duality;

import org.junit.jupiter.api.Test;

public class PubSubTest {

	@Test
	public void pubSub() {
		Publisher publisher = new Publisher();
		Subscriber subscriber = new Subscriber();

		publisher.subscribe(subscriber);
	}
}
