package com.genius.payroll;

public class AddHourlyEmployee extends AddEmployeeTransaction {

	private double hourlyRate;

	public AddHourlyEmployee(int itsEmpId, String itsName, String itsAddress, double hourlyRate) {
		super(itsEmpId, itsName, itsAddress);
		this.hourlyRate = hourlyRate;
	}

	public PaymentClassfication getClassification() {
		return null;
	}
}
