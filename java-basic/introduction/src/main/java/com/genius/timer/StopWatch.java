package com.genius.timer;

public class StopWatch {
    public long startTime;
    public long endTime;

    public Time getElapsedTime() {
        return new Time(endTime - startTime);
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}