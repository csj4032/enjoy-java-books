package com.genius.payroll;

public class ChangeHoldTransaction extends ChangeMethodTransaction {

	public ChangeHoldTransaction(long empId) {
		super(empId);
	}

	@Override
	PaymentMethod getMethod() {
		return new HoldMethod();
	}
}
