package com.genius.calendar;

import com.genius.contact.Calculator;

public class Calendar {

	private final Year year;
	private final Month month;

	public Calendar() {
		this(1,1);
	}

	public Calendar(int yearValue, int monthValue) {
		this.year = new Year(yearValue);
		this.month = new Month(yearValue, monthValue);
	}

	public int getYear() {
		return year.getValue();
	}

	public int getMonth() {
		return month.getValue();
	}

	public int getDayOfMonth() {
		return month.getDayOfMonth();
	}

	public int monthLength() {
		return month.length();
	}
}
