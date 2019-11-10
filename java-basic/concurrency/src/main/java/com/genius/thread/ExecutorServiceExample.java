package com.genius.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.genius.thread.ThreadExample.f;
import static com.genius.thread.ThreadExample.g;

public class ExecutorServiceExample {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		int x = 1337;
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		Future<Integer> y = executorService.submit(() -> f(x));
		Future<Integer> z = executorService.submit(() -> g(x));
		System.out.println(y.get() + z.get());
		executorService.shutdown();
	}
}
