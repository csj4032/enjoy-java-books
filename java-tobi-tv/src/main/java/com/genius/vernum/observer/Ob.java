package com.genius.vernum.observer;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@SuppressWarnings("deprecation")
public class Ob {

//	public static void main(String[] args) {
//		Iterable<Integer> iter = () ->
//				new Iterator<Integer>() {
//					int i = 0 ;
//					final static int MAX = 10;
//					public boolean hasNext() { return i < MAX;}
//					public Integer next() { return ++i;}
//				};
//
//		for (Integer i : iter) {
//			System.out.println(i);
//		}
//
//		Integer[] aa = new Integer[]{1,2,3};
//
//		for (Integer i : aa) {
//			System.out.println(i);
//		}
//	}

	static class IntObservable extends Observable implements Runnable {
		@Override
		public void run() {
			IntStream.rangeClosed(1, 10).forEach(this::noticing);
		}

		public void noticing(Object arg) {
			setChanged();
			notifyObservers(arg);
		}
	}

	public static void main(String[] args) {
		Observer ob = (o, a) -> System.out.println(Thread.currentThread().getName() + " " + a);
		IntObservable io = new IntObservable();
		io.addObserver(ob);

		ExecutorService es = Executors.newSingleThreadExecutor();
		es.execute(io);
		es.shutdown();
	}
}