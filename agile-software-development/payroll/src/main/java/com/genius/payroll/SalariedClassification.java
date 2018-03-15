package com.genius.payroll;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SalariedClassification implements PaymentClassification {

	private double salary;

	@Override
	public double calculatePay(Paycheck paycheck) {
		return salary;
	}
}
