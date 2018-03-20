package com.genius.payroll;

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