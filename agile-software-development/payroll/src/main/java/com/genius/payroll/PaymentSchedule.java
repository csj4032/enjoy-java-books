package com.genius.payroll;

import java.time.LocalDate;

public interface PaymentSchedule {

	boolean isPayDate(LocalDate date);

	LocalDate getPayPeriodStartDate(LocalDate payPeriodEndDate);
}
