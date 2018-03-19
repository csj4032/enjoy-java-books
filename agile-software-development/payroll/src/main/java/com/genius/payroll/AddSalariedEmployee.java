package com.genius.payroll;

public class AddSalariedEmployee extends AddEmployeeTransaction {

	private double salary;

	public AddSalariedEmployee(long itsEmpId, String itsName, String itsAddress, double salary) {
		super(itsEmpId, itsName, itsAddress);
		this.salary = salary;
	}

	public PaymentClassification getClassification() {
		return new SalariedClassification(salary);
	}

	public PaymentSchedule getSchedule() {
		return new MonthlySchedule();
	}
}
