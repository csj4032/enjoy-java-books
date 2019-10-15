package com.genius.timer;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StopWatchTest {

	private StopWatch stopWatch;

	@Test
	@Order(1)
	public void creatTest() {
		stopWatch = new StopWatch();
	}

	@Test
	public void elapsedTime() throws InterruptedException {
		stopWatch = new StopWatch();
		stopWatch.setStartTime(System.nanoTime());
		Thread.sleep(5000);
		stopWatch.setEndTime(System.nanoTime());

		Time time = stopWatch.getElapsedTime();
		System.out.println("Elapsed Time Nano : " + time.getNanoTime());
		System.out.println("Elapsed Time Mill : " + time.getMillTime());
		System.out.println("Elapsed Time Second : " + time.getSecondTime());
		System.out.println("Elapsed Time Second : " + Duration.of(time.getNanoTime(), ChronoUnit.NANOS).getSeconds());
	}
}

