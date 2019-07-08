package com.genius.calendar;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class Calendar {

	private static final LocalDate startDate = LocalDate.of(1, 1, 1);
	private static final int dayOfWeek = 7;
	private static final int dayOfYear = 365;
	private final LocalDate currentDate;

	public Calendar() {
		this(LocalDate.now().getYear(), LocalDate.now().getMonth().getValue());
	}

	public Calendar(int year, int month) {
		currentDate = LocalDate.of(year, month, 1);
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public long getDayOfMonth() {
		return (getDaysUntil() + 1) % dayOfWeek;
	}

	public long getDaysUntil() {
		return getDayOfYears() + getDayOfYear();
	}

	public long getDayOfYear() {
		long dayOfYear = 0;
		for (int i = 0; i < currentDate.getMonthValue() - 1; i++) {
			dayOfYear += getMonthLength(Month.of(i + 1));
		}
		return dayOfYear;
	}

	public boolean isLeapYear() {
		return ((currentDate.getYear() & 3) == 0) && ((currentDate.getYear() % 100 != 0 || currentDate.getYear() % 400 == 0));
	}

	public int getCurrentMonthLength() {
		return getMonthLength(currentDate.getMonth());
	}

	private int getMonthLength(Month month) {
		switch (month) {
			case FEBRUARY:
				return (isLeapYear() ? 29 : 28);
			case APRIL:
			case JUNE:
			case SEPTEMBER:
			case NOVEMBER:
				return 30;
			default:
				return 31;
		}
	}

	public int getYear() {
		return currentDate.getYear();
	}

	public int getMonth() {
		return currentDate.getMonthValue();
	}

	public long getDayOfYears() {
		long until = getYearsUntil();
		return (until * dayOfYear) + (until / 4) - ((until / 100) - (until / 400));
	}

	private long getYearsUntil() {
		return startDate.until(currentDate, ChronoUnit.YEARS);
	}
}