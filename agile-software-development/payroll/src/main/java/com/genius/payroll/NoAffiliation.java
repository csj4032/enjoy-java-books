package com.genius.payroll;

public class NoAffiliation implements Affiliation {

	@Override
	public double calculateDeductions(Paycheck paycheck) {
		return 0;
	}
}
