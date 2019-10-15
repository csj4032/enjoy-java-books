package com.genius.contact;

import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.time.LocalDateTime;

public class Call {

	private DateTimeInterval interval;

	public Call(@NotNull LocalDateTime from, @NotNull LocalDateTime to) {
		this.interval = DateTimeInterval.of(from, to);
	}

	public Duration getDuration() {
		return interval.duration();
	}
}