package com.genius.payroll.transaction;

import com.genius.payroll.PaymentMethod;
import com.genius.payroll.domain.Employee;

public abstract class ChangeMethodTransaction extends ChangeEmployeeTransaction {

	public ChangeMethodTransaction(long empId) {
		super(empId);
	}

	abstract PaymentMethod getMethod();

	@Override
	protected void change(Employee employee) {
		employee.setPaymentMethod(getMethod());
	}
}