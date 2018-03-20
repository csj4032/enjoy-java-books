package com.genius.payroll;

public class HoldMethod implements PaymentMethod {

	@Override
	public void pay(Paycheck paycheck) {
		paycheck.setField("Disposition", "Hold");
	}
}