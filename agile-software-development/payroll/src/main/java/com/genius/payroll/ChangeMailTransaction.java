package com.genius.payroll;

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
