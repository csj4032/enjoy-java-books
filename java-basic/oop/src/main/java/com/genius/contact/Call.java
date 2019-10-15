package com.genius.contact;

import java.time.Duration;
import java.time.LocalDateTime;

public class Call {

	private DateTimeInterval interval;

	public Call(LocalDateTime from, LocalDateTime to) {
		this.interval = DateTimeInterval.of(from, to);
	}

	public Duration getDuration() {
		return interval.duration();
	}
}