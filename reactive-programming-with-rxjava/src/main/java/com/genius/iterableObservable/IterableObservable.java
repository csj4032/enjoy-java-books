package com.genius.iterableObservable;

import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("I known Observable")
public class IterableObservable {

	static class IntObservable extends Observable implements Runnable {
		@Override
		public void run() {
			for (int i = 1; i <= 10; i++) {
				setChanged();
				notifyObservers(i);
			}
		}
	}

	public static void main(String[] args) {
		IntObservable observable = new IntObservable();
		observable.addObserver((Observable o, Object arg) -> System.out.println(Thread.currentThread().getName() + " " + arg));
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.execute(observable);
		executorService.shutdown();
		System.out.println(Thread.currentThread().getName());
	}
}