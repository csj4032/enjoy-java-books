package com.genius.calendar;

public class CalendarUtils {

	private CalendarUtils() {
	}

	public static int getDaysOfYears(int value) {
		int totalDays = 0;
		for (int i = 1; i < value; i++) {
			totalDays += isLeap(i) ? 366 : 365;
		}
		return totalDays;
	}

	public static boolean isLeap(int value) {
		return ((value & 3) == 0) && ((value % 100 != 0 || value % 400 == 0));
	}
}
