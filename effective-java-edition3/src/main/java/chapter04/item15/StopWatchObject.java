package chapter04.item15;

public class StopWatchObject {

	private long startTime;
	private long stopTime;

	public void start() {
		startTime = System.nanoTime();
	}

	public void stop() {
		stopTime = System.nanoTime();
	}

	public Time getElapsedTime() {
		return new Time(stopTime - startTime);
	}
}
