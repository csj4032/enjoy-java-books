package com.genius.payroll;

import java.time.LocalDate;

public class MonthlySchedule implements PaymentSchedule {

	@Override
	public boolean isPayDate(LocalDate payDate) {
		return isLastDayOfMonth(payDate);
	}

	private boolean isLastDayOfMonth(LocalDate payDate) {
		return payDate.plusDays(1).getMonth().equals(payDate.getMonth());
	}

	@Override
	public LocalDate getPayPeriodStartDate(LocalDate payPeriodEndDate) {
		int lastDayOfMonth = payPeriodEndDate.getDayOfMonth();
		LocalDate firstDayOfMonth = payPeriodEndDate.minusDays(lastDayOfMonth - 1);
		return firstDayOfMonth;
	}
}