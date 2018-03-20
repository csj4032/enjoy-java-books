package com.genius.payroll;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Paycheck {

	private LocalDate payPeriodStartDate;
	private LocalDate payPeriodEndDate;
	private double grossPay;
	private double netPay;
	private double deductions;
	private Map<String, String> fields = new HashMap<>();

	public Paycheck(LocalDate payPeriodStart, LocalDate payPeriodEnd) {
		this.payPeriodStartDate = payPeriodStart;
		this.payPeriodEndDate = payPeriodEnd;
	}

	public void setField(String key, String value) {
		fields.put(key, value);
	}

	public String getField(String key) {
		return fields.get(key);
	}
}
