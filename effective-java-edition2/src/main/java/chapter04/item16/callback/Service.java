package chapter04.item16.callback;

public class Service {

	void performAsync(SomethingWithCallback callback) {
		new Thread(() -> {
			perform();
			callback.call();
		}).start();
	}

	void perform() {
		System.out.println("Service is being performed.");
	}
}
