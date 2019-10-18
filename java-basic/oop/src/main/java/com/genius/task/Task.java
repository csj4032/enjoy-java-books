package com.genius.task;

import java.time.LocalDateTime;

public class Task {

	private String title;
	private LocalDateTime date;
	private Boolean isComplete = false;

	public Task(String title, LocalDateTime date) {
		this.title = title;
		this.date = date;
	}

	public void toggle() {
		this.isComplete = !this.isComplete;
	}

	public String getTitle() {
		return title;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public void setComplete(Boolean complete) {
		isComplete = complete;
	}
}
