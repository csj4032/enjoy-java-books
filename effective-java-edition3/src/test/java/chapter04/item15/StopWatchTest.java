package chapter04.item15;

import org.junit.Test;

public class StopWatchTest {

	@Test
	public void ElapsedTimeTest() {
		var stopwatch = new StopWatch();
		stopwatch.start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {

		}
		stopwatch.stop = System.currentTimeMillis();
		System.out.println("elapsed time  : " + (stopwatch.stop - stopwatch.start));

	}
}
