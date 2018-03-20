package com.genius.payroll.transaction;

import com.genius.payroll.MonthlySchedule;
import com.genius.payroll.PaymentClassification;
import com.genius.payroll.PaymentSchedule;
import com.genius.payroll.SalariedClassification;
import com.genius.payroll.transaction.AddEmployeeTransaction;

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
