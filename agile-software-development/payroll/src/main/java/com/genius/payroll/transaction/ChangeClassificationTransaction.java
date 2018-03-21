package com.genius.payroll.transaction;

import com.genius.payroll.PaymentClassification;
import com.genius.payroll.PaymentSchedule;
import com.genius.payroll.domain.Employee;

public abstract class ChangeClassificationTransaction extends ChangeEmployeeTransaction {

	public ChangeClassificationTransaction(long empId) {
		super(empId);
	}

	abstract PaymentClassification getClassification();

	abstract PaymentSchedule getSchedule();

	public void change(Employee employee) {
		employee.setClassification(getClassification());
		employee.setSchedule(getSchedule());
	}
}
