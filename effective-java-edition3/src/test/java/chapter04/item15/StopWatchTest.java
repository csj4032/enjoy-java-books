package chapter04.item15;

import org.junit.Test;

public class StopWatchTest {

	@Test
	public void ElapsedTime() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.startTime = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
		}
		stopWatch.stopTime = System.currentTimeMillis();
		System.out.println(stopWatch.getElapsedTime());

		stopWatch.startTime = System.nanoTime();
		for (int i = 0; i < 10000000; i++) {
		}
		stopWatch.stopTime = System.nanoTime();
		System.out.println(stopWatch.getElapsedTime());

		StopWatchObject stopWatchObject = new StopWatchObject();
		stopWatchObject.start();
		for (int i = 0; i < 10000000; i++) {
		}
		stopWatchObject.stop();
		Time time = stopWatchObject.getElapsedTime();
		System.out.println(time.getNano());
	}
}