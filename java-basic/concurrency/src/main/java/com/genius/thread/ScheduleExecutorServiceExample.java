package com.genius.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ScheduleExecutorServiceExample {

	public static void main(String[] args) {
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
		work1();
		scheduledExecutorService.schedule(ScheduleExecutorServiceExample::work2, 10, TimeUnit.SECONDS);
		scheduledExecutorService.shutdown();
	}

	public static void work1() {
		log.info("Hello form Work1");
	}

	public static void work2() {
		log.info("Hello from Work2");
	}
}
