package com.genius;

import java.time.LocalDateTime;

public class Book {

	private final LocalDateTime start;
	private final LocalDateTime end;
	private final Room room;

	public Book(Room room, LocalDateTime start, LocalDateTime end) {
		this.room = room;
		this.start = start;
		this.end = end;
	}
}
