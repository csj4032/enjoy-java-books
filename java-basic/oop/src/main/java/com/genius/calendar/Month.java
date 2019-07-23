package com.genius.calendar;

import static com.genius.calendar.Year.getDaysOfYears;
import static com.genius.calendar.Year.isLeap;

public class Month {

	private final int year;
	private final int month;

	public Month(int year, int month) {
		this.year = year;
		this.month = month;
	}

	public int getDayOfMonth() {
		return (getDaysOfYearsAndFirstDayOfYear() + 1) % 7;
	}

	public int length() {
		return length(isLeap(year));
	}

	public int getValue() {
		return month;
	}

	public int getDaysOfYearsAndFirstDayOfYear() {
		return getDaysOfYears(year) + firstDayOfYear(isLeap(year));
	}

	public int firstDayOfYear(boolean isLeap) {
		int leap = isLeap ? 1 : 0;
		switch (month) {
			case 1:
				return 0;
			case 2:
				return 31 + leap;
			case 3:
				return 59 + leap;
			case 4:
				return 89 + leap;
			case 5:
				return 120 + leap;
			case 6:
				return 151 + leap;
			case 7:
				return 181 + leap;
			case 8:
				return 212 + leap;
			case 9:
				return 243 + leap;
			case 10:
				return 273 + leap;
			case 11:
				return 304 + leap;
			case 12:
			default:
				return 334 + leap;
		}
	}

	private int length(boolean isLeap) {
		switch (month) {
			case 2:
				return (isLeap ? 29 : 28);
			case 4:
			case 6:
			case 9:
			case 11:
				return 30;
			default:
				return 31;
		}
	}
}
