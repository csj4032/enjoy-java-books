package com.genius.payroll;

import lombok.Getter;

@Getter
public class AddHourlyEmployee extends AddEmployeeTransaction {

	private double hourlyRate;

	public AddHourlyEmployee(long empId, String name, String address, double hourlyRate) {
		super(empId, name, address);
		this.hourlyRate = hourlyRate;
	}

	@Override
	public PaymentClassification getClassification() {
		return new HourlyClassification(hourlyRate);
	}

	@Override
	public PaymentSchedule getSchedule() {
		return null;
	}
}
