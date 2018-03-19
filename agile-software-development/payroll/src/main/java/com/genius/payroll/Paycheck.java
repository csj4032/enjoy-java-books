package com.genius.payroll;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
public class Paycheck {

	private Date payPeriodStartDate;
	private Date payPeriodEndDate;
	private double grossPay;
	private double netPay;
	private double deductions;
	private Map<String, String> fields;

	public Paycheck(Date payPeriodStart, Date payPeriodEnd) {
		this.payPeriodStartDate = payPeriodStart;
		this.payPeriodEndDate = payPeriodEnd;
	}

	public void setFields(String key, String value) {
		fields.put(key, value);
	}
}
