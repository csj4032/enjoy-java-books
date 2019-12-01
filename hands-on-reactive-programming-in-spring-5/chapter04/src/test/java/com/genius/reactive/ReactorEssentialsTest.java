package com.genius.reactive;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

@Slf4j
public class ReactorEssentialsTest {

	private final Random random = new Random();

	@Test
	@Disabled
	public void endlessStream() {
		Flux.interval(Duration.ofMillis(1)).collectList().block();
	}

	@Test
	@Disabled
	public void endlessStream2() {
		Flux.range(1, 5).repeat().doOnNext(e -> log.info("E: {}", e)).take(100).blockLast();
	}

	@Test
	@Disabled
	public void simpleSubscribe() {
		Flux.just("A", "B", "C").subscribe(
				System.out::println,
				errorIgnored -> {
				},
				() -> System.out.println("onComplete"));
	}

	@Test
	@Disabled
	public void managingDemand() {
		Flux.range(1, 100).subscribe(
				data -> log.info("onNext : {}", data),
				err -> {
				},
				() -> log.info("onComplete"),
				subscription -> {
					subscription.request(4);
					subscription.cancel();
				}
		);
	}

	@Test
	@Disabled
	public void managingSubscription() throws InterruptedException {
		Disposable disposable = Flux.interval(Duration.ofMillis(50))
				.doOnCancel(() -> log.info("Cancelled"))
				.subscribe(data -> log.info("onNext: {}", data));
		Thread.sleep(200);
		disposable.dispose();
	}

	@Test
	@Disabled
	public void subscribingOnStream() throws Exception {
		Subscriber<String> subscriber = new Subscriber<>() {
			volatile Subscription subscription;

			@Override
			public void onSubscribe(Subscription s) {
				subscription = s;
				log.info("initial request for 1 element");
				subscription.request(1);
			}

			public void onNext(String s) {
				log.info("onNext: {}", s);
				log.info("requesting 1 more element");
				subscription.request(1);
			}

			public void onComplete() {
				log.info("onComplete");
			}

			public void onError(Throwable t) {
			}
		};

		Flux<String> stream = Flux.just("Hello", "world", "!");
		stream.subscribe(subscriber);
		Thread.sleep(100);
	}

	@Test
	@Disabled
	public void indexElements() {
		Flux.range(2018, 5)
				.timestamp()
				.index()
				.subscribe(e -> log.info("index: {}, ts: {}, value: {}", e.getT1(), Instant.ofEpochMilli(e.getT2().getT1()), e.getT2().getT2()));
	}

	@Test
	@Disabled
	public void collectionSort() {
		Flux.just(1, 6, 2, 8, 3, 1, 5, 1).collectSortedList(Comparator.reverseOrder()).subscribe(System.out::println);
	}

	@Test
	@Disabled
	public void findingIfThereIsEvenElements() {
		Flux.just(3, 5, 7, 9, 11, 15, 16, 17).any(e -> e % 2 == 0).subscribe(hasEvens -> log.info("Has evens: {}", hasEvens));
		Flux.just(3, 5, 7, 9, 11, 15, 16, 17).all(e -> e % 2 == 0).subscribe(hasEvens -> log.info("All evens: {}", hasEvens));
	}

	@Test
	@Disabled
	public void reduceExample() {
		Flux.range(1, 10).reduce(0, (acc, elem) -> acc + elem).subscribe(result -> log.info("Result: {}", result));
		Flux.range(1, 10).scan(0, (acc, elem) -> acc + elem).subscribe(result -> log.info("Result: {}", result));
	}

	@Test
	@Disabled
	public void runningAverageExample() {
		int bucketSize = 5;
		Flux.range(1, 500)
				.index()
				.scan(new int[bucketSize], (acc, elem) -> {
					acc[(int) (elem.getT1() % bucketSize)] = elem.getT2();
					return acc;
				})
				.skip(bucketSize)
				.map(array -> {
					log.info("bucket : {}", array);
					return Arrays.stream(array).sum() * 1.0 / bucketSize;
				})
				.subscribe(av -> log.info("bucket : {},  Running average : {}", av));
	}

	@Test
	@Disabled
	public void thenOperator() {
		Flux.just(1, 2, 3)
				.thenMany(Flux.just(5, 6))
				.subscribe(e -> log.info("onNext: {}", e));
	}

	@Test
	@Disabled
	public void sampleExample() {
		Flux.just("user-1", "user-2", "user-3").flatMap(u -> requestBooks(u));
	}

	private Flux<String> requestBooks(String user) {
		return Flux.range(1, random.nextInt(3) + 1)
				.delayElements(Duration.ofMillis(3))
				.map(i -> "book-" + i);
	}

	@Test
	@Disabled
	public void combineLatestOperator() {
		Flux.concat(
				Flux.range(1, 3),
				Flux.range(4, 2),
				Flux.range(6, 5)
		).subscribe(e -> log.info("onNext: {}", e));
	}

	@Test
	public void mergeOperator() {
		Flux.merge(
				Flux.range(1, 3),
				Flux.range(4, 2),
				Flux.range(6, 5)
		).subscribe(e -> log.info("onNext: {}", e));
	}
}