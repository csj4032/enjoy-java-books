package com.genius.payroll;

import java.time.LocalDate;

public class WeeklySchedule implements PaymentSchedule {

	@Override
	public boolean isPayDate(LocalDate date) {
		return false;
	}

	@Override
	public LocalDate getPayPeriodStartDate(LocalDate payPeriodEndDate) {
		return null;
	}
}
