package chapter04.item15;

import org.junit.jupiter.api.Test;


public class StopWatchTest {

	@Test
	public void ElapsedTime() {
		StopWatch stopWatch = new StopWatch();
		System.out.println(stopWatch.toString());
		stopWatch.setStopTime(-1);
		for (int i = 0; i < 10000000; i++) {
		}
		stopWatch.setStopTime(System.nanoTime());
		System.out.println(stopWatch.getElapsedTime());

		int ints = 360;
		Integer integers = 360;

		if (integers == ints) {
			System.out.println("!!!!");
		}
	}

	@Test
	public void ElapsedTimeA() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.setStartTime(System.currentTimeMillis());
		for (int i = 0; i < 10000000; i++) {
		}
		stopWatch.setStopTime(System.nanoTime());
		System.out.println(stopWatch.getElapsedTime());
	}

	@Test
	public void ElapsedTimeB() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.setStartTime(System.nanoTime());
		for (int i = 0; i < 10000000; i++) {
		}
		stopWatch.setStopTime(System.nanoTime());
	}

	@Test
	public void ElapsedTimeC() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.setStartTime(System.nanoTime());
		for (int i = 0; i < 10000000; i++) {
		}
		stopWatch.setStopTime(System.nanoTime());
		System.out.println(stopWatch.getElapsedTime());
	}

	@Test
	public void ElapsedTimeD() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.setStartTime(System.nanoTime());
		for (int i = 0; i < 10000000; i++) {
		}
		stopWatch.setStopTime(System.nanoTime());
		System.out.println(stopWatch.getElapsedTime());
	}
}