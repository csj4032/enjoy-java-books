package com.genius.payroll;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeeklySchedule implements PaymentSchedule {

	@Override
	public boolean isPayDate(LocalDate date) {
		return date.getDayOfWeek().equals(DayOfWeek.FRIDAY);
	}

	@Override
	public LocalDate getPayPeriodStartDate(LocalDate payPeriodEndDate) {
		return payPeriodEndDate.minusDays(6);
	}
}