package com.genius.timer;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StopWatchTest {

    @Test
    public void creatTest() {
        StopWatch stopWatch = new StopWatch();
    }

    @Test
    public void elapsedTime() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.startTime = System.nanoTime();

        Thread.sleep(1000);

        stopWatch.endTime = System.nanoTime();
        System.out.println("Elapsed Time : " + (stopWatch.endTime - stopWatch.startTime));

        stopWatch.startTime = System.nanoTime();
        Thread.sleep(1000);
        stopWatch.endTime = System.nanoTime();
        System.out.println("Elapsed Time : " + stopWatch.getElapsedTime().getNanoTime());

        stopWatch.setStartTime(System.nanoTime());
        Thread.sleep(1000);
        stopWatch.setEndTime(System.nanoTime());
        System.out.println("Elapsed Time : " + stopWatch.getElapsedTime().getNanoTime());
        System.out.println("Elapsed Time : " + stopWatch.getElapsedTime().getMillTime());

    }
}