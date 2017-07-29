package net.jcip.examples;

public class CounterTesting {

	public static void main(String[] args) {
		Counter counter = new Counter();
		Thread threadA = new CounterThread(counter);
		Thread threadB = new CounterThread(counter);

		threadA.start();
		threadB.start();
	}
}

class CounterThread extends Thread {

	protected Counter counter = null;

	public CounterThread(Counter counter) {
		this.counter = counter;
	}

	public void run() {
		for (long i = 0L; i < 5L; i++) {
			System.out.println(this.getName() + " : " + counter.getValue());
			counter.increment();
		}
	}
}