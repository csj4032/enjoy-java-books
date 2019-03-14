package chapter04.item15;

import lombok.ToString;

@ToString
public class Time {

	private long nano;

	public Time(long nano) {
		this.nano = nano;
	}

	public long getNano() {
		return nano;
	}

	public long getMacro() {
		return nano / 1000;
	}

	public long getMillis() {
		return nano / 1000_000;
	}
}