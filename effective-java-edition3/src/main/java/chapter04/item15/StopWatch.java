package chapter04.item15;

public class StopWatch {
	public long startTime;
	public long startNanoTime;
	public long stopTime;
	public long stopNanoTime;

	public long getElapsedTime() {
		return stopTime - startTime;
	}

	public long getElapsedNanoTime() {
		return stopNanoTime - startNanoTime;
	}
}