package com.genius.hello;

import java.util.List;

public class HelloWorld {

	private String message;

	public void hello() {
		System.out.println("Hello! " + message);
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setHolidays(List<Holiday> holidays) {
	}
}
