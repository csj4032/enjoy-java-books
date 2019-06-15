package com.genius.duality;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;

public class Publisher implements Flow.Publisher {

	Iterable<Integer> itr = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
	ExecutorService executorService = Executors.newSingleThreadExecutor();

	@Override
	public void subscribe(Flow.Subscriber subscriber) {
		Iterator<Integer> it = itr.iterator();
		subscriber.onSubscribe(new Flow.Subscription() {
			@Override
			public void request(long n) {
				executorService.execute(() -> {
					int i = 0;
					while (i++ < n) {
						if (it.hasNext()) {
							subscriber.onNext(it.next());
						} else {
							subscriber.onComplete();
							executorService.shutdown();
							break;
						}
					}
				});
			}

			@Override
			public void cancel() {

			}
		});
	}
}
