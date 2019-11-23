package com.genius.reactive.rxPubsubApp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import rx.Observable;

import java.util.Random;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Slf4j
@Component
public class TemperatureSensor {

	private final Random rnd = new Random();
	private final Observable dataStream = Observable
			.range(0, Integer.MAX_VALUE)
			.concatMap(ignore -> Observable.just(1).delay(rnd.nextInt(5000), MILLISECONDS).map(ignore2 -> this.probe()))
			.publish()
			.refCount();

	public Observable temperatureStream() {
		return dataStream;
	}

	private Temperature probe() {
		return new Temperature(16 + rnd.nextGaussian() * 10);
	}
}
