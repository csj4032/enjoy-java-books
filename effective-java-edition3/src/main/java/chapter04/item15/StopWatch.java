package chapter04.item15;

public class StopWatch {

	private long startTime;
	private long stopTime;

	public Time getElapsedTime() {
		if (startTime > stopTime) {
			throw new RuntimeException("");
		}
		return new Time(stopTime - startTime);
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public void setStopTime(long stopTime) {
		this.stopTime = stopTime;
	}
}