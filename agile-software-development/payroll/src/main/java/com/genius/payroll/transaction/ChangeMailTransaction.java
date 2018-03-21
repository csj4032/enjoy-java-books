package com.genius.payroll.transaction;

import com.genius.payroll.MailMethod;
import com.genius.payroll.PaymentMethod;

public class ChangeMailTransaction extends ChangeMethodTransaction {

	private String address;

	public ChangeMailTransaction(long empId, String address) {
		super(empId);
		this.address = address;
	}

	@Override
	PaymentMethod getMethod() {
		return new MailMethod(address);
	}
}
