package com.genius.agile.timer;

import com.genius.intro.timer.StopWatch;

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
        stopWatch.startTime = System.currentTimeMillis();

        Thread.sleep(1000);

        stopWatch.endTime = System.currentTimeMillis();
        System.out.println("Elapsed Time : " + (stopWatch.endTime - stopWatch.startTime));

        stopWatch.startTime = System.currentTimeMillis();
        Thread.sleep(1000);
        stopWatch.endTime = System.currentTimeMillis();
        System.out.println("Elapsed Time : " + stopWatch.getElapsedTime());

        stopWatch.setStartTime(System.currentTimeMillis());
        Thread.sleep(1000);
        stopWatch.setEndTime(System.currentTimeMillis());
        System.out.println("Elapsed Time : " + stopWatch.getElapsedTime());
    }
}