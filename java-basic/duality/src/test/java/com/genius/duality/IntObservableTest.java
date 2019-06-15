package com.genius.duality;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IntObservableTest {

	@Test
	public void observable() {
		IntObservable intObservable = new IntObservable();
		intObservable.addObserver((o, a) -> System.out.println(Thread.currentThread().getName() + " " + a));
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.execute(intObservable);

		System.out.println(Thread.currentThread().getName() + " EXIT");
		executorService.shutdown();
	}
}
