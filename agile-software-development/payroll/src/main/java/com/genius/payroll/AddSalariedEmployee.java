package com.genius.payroll;

public class AddSalariedEmployee extends AddEmployeeTransaction {

	private double salary;

	public AddSalariedEmployee(long empId, String name, String address, double salary) {
		super(empId, name, address);
		this.salary = salary;
	}

	public PaymentClassification getClassification() {
		return new SalariedClassification(salary);
	}

	public PaymentSchedule getSchedule() {
		return new MonthlySchedule();
	}
}
