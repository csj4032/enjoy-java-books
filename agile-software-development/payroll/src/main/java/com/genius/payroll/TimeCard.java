package com.genius.payroll;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class TimeCard {

	private final LocalDate date;
	private final double hours;

	public TimeCard(LocalDate date, double hours) {
		this.date = date;
		this.hours = hours;
	}
}