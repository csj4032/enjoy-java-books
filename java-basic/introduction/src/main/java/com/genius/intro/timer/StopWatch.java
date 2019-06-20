package com.genius.intro.timer;

public class StopWatch {
    public long startTime;
    public long endTime;

    public long getElapsedTime() {
        return endTime - startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
