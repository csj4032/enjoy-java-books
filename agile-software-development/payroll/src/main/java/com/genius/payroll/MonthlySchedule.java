package com.genius.payroll;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class MonthlySchedule implements PaymentSchedule {

	@Override
	public boolean isPayDate(LocalDate payDate) {
		return isLastDayOfMonth(payDate);
	}

	private boolean isLastDayOfMonth(LocalDate payDate) {
		//return !payDate.plusDays(1).getMonth().equals(payDate.getMonth());
		return payDate.equals(payDate.with(TemporalAdjusters.lastDayOfMonth()));
	}

	@Override
	public LocalDate getPayPeriodStartDate(LocalDate payPeriodEndDate) {
		int lastDayOfMonth = payPeriodEndDate.getDayOfMonth();
		LocalDate firstDayOfMonth = payPeriodEndDate.minusDays(lastDayOfMonth - 1);
		return firstDayOfMonth;
	}
}