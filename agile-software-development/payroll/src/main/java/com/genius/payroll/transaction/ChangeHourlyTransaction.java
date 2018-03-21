package com.genius.payroll.transaction;

import com.genius.payroll.HourlyClassification;
import com.genius.payroll.PaymentClassification;
import com.genius.payroll.PaymentSchedule;
import com.genius.payroll.WeeklySchedule;

public class ChangeHourlyTransaction extends ChangeClassificationTransaction {

	private double hourlyRate;

	public ChangeHourlyTransaction(long empId, double hourlyRate) {
		super(empId);
		this.hourlyRate = hourlyRate;
	}

	@Override
	PaymentClassification getClassification() {
		return new HourlyClassification(hourlyRate);
	}

	@Override
	PaymentSchedule getSchedule() {
		return new WeeklySchedule();
	}
}
