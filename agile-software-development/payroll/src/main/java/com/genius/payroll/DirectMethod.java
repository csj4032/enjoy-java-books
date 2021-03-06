package com.genius.payroll;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DirectMethod implements PaymentMethod {

	private String bank;
	private String account;

	public DirectMethod(String bank, String account) {
		this.bank = bank;
		this.account = account;
	}

	@Override
	public void pay(Paycheck paycheck) {
		paycheck.setField("Disposition", "Direct");
	}
}