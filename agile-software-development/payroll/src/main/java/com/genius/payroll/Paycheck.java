package com.genius.payroll;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

@Getter
@Setter
public class Paycheck {

	private LocalDate payPeriodStartDate;
	private LocalDate payPeriodEndDate;
	private double grossPay;
	private double netPay;
	private double deductions;
	private Map<String, String> fields;

	public Paycheck(LocalDate payPeriodStart, LocalDate payPeriodEnd) {
		this.payPeriodStartDate = payPeriodStart;
		this.payPeriodEndDate = payPeriodEnd;
	}

	public void setFields(String key, String value) {
		fields.put(key, value);
	}
}
