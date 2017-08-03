package net.jcip.examples;

public class HiddenIteratorTesting {

	public static void main(String[] args) {
		HiddenIterator hiddenIterator = new HiddenIterator();

		new Thread(() -> {
			System.out.println("@@@@");
			hiddenIterator.addTenThings();
		}).start();

		new Thread(() -> {
			System.out.println("!!");
			for (int i = 0; i < 1000000; i++) {
				hiddenIterator.remove(i);
			}
		}).start();
	}
}