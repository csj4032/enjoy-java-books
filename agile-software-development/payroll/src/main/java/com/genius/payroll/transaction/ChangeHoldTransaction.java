package com.genius.payroll.transaction;

import com.genius.payroll.HoldMethod;
import com.genius.payroll.PaymentMethod;

public class ChangeHoldTransaction extends ChangeMethodTransaction {

	public ChangeHoldTransaction(long empId) {
		super(empId);
	}

	@Override
	PaymentMethod getMethod() {
		return new HoldMethod();
	}
}