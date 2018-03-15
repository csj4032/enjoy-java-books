package com.genius.payroll;

public class NoAffiliation implements Affiliation {

	@Override
	public double CalculateDeductions(Paycheck paycheck) {
		return 0;
	}
}
