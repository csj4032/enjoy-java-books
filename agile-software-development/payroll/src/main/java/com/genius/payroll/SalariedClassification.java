package com.genius.payroll;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SalariedClassification implements PaymentClassification {

	private double salary;

	public SalariedClassification(double salary) {
		this.salary = salary;
	}

	@Override
	public double calculatePay(Paycheck paycheck) {
		return salary;
	}
}
