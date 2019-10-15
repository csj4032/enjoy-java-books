package com.genius.contact;

import java.time.Duration;
import java.time.LocalDateTime;

public class DateTimeInterval {

	private final LocalDateTime from;
	private final LocalDateTime to;

	private DateTimeInterval(LocalDateTime from, LocalDateTime to) {
		this.from = from;
		this.to = to;
	}

	public static DateTimeInterval of(LocalDateTime from, LocalDateTime to) {
		return new DateTimeInterval(from, to);
	}

	public Duration duration() {
		return Duration.between(from, to);
	}
}
