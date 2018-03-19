package com.genius.payroll;

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
