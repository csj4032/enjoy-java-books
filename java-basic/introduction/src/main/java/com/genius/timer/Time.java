package com.genius.timer;

public class Time {

	private long nano;

	public Time(long nano) {
		this.nano = nano;
	}

	public long getNanoTime() {
		return nano;
	}

	public long getMillTime() {
		return nano / 1_000_000;
	}

	public long getSecondTime() {
		return nano / 1_000_000_000;
	}
}
