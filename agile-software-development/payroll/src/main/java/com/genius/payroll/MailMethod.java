package com.genius.payroll;

import lombok.Getter;

public class MailMethod implements PaymentMethod {

	@Getter
	private String address;

	public MailMethod(String address) {
		this.address = address;
	}

	@Override
	public void pay(Paycheck paycheck) {
		paycheck.setField("Disposition", "Mail");
	}
}