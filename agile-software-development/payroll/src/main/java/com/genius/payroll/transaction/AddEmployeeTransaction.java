package com.genius.payroll.transaction;

import com.genius.payroll.*;
import com.genius.payroll.domain.Employee;
import com.genius.payroll.persistence.PayrollDatabase;

public abstract class AddEmployeeTransaction implements Transaction {

	private long empId;
	private String name;
	private String address;

	public AddEmployeeTransaction(long empId, String name, String address) {
		this.empId = empId;
		this.name = name;
		this.address = address;
	}

	protected abstract PaymentClassification getClassification();

	protected abstract PaymentSchedule getSchedule();

	@Override
	public void execute() {
		PaymentClassification paymentClassification = getClassification();
		PaymentSchedule paymentSchedule = getSchedule();
		PaymentMethod paymentMethod = new HoldMethod();
		Employee employee = new Employee(empId, name, address);
		employee.setClassification(paymentClassification);
		employee.setSchedule(paymentSchedule);
		employee.setPaymentMethod(paymentMethod);
		PayrollDatabase.addEmployee(empId, employee);
	}
}