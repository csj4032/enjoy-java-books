package chapter04.item15;

public class StopWatch {
	public long startTime;
	public long stopTime;

	public long getElapsedTime() {
		return stopTime - startTime;
	}
}