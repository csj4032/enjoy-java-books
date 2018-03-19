package com.genius.payroll;

import lombok.Getter;

@Getter
public class AddHourlyEmployee extends AddEmployeeTransaction {

	private double hourlyRate;
	private PaymentClassification hourlyClassification;

	public AddHourlyEmployee(long empId, String name, String address, double hourlyRate) {
		super(empId, name, address);
		this.hourlyRate = hourlyRate;
		hourlyClassification = new HourlyClassification(hourlyRate);
	}

	@Override
	public PaymentClassification getClassification() {
		return hourlyClassification;
	}

	@Override
	public PaymentSchedule getSchedule() {
		return null;
	}
}
