package chapter10.item69;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

public class ConcurrentTimer {

	private ConcurrentTimer() {
	}

	public static long time(Executor executor, int concurrency, final Runnable action) throws InterruptedException {
		final CountDownLatch ready = new CountDownLatch(concurrency);
		final CountDownLatch start = new CountDownLatch(1);
		final CountDownLatch done = new CountDownLatch(concurrency);

		for (int i = 0; i < concurrency; i++) {
			executor.execute(() -> {
				ready.countDown();
				try {
					start.await();
					action.run();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				} finally {
					done.countDown();
				}
			});
		}

		ready.await();
		long startNanos = System.nanoTime();
		start.countDown();
		done.await();
		return System.nanoTime() - startNanos;
	}
}
