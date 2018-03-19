package com.genius.payroll;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class SalesReceipt {

	private final LocalDate saleDate;
	private final double amount;

	public SalesReceipt(LocalDate saleDate, double amount) {
		this.saleDate = saleDate;
		this.amount = amount;
	}
}
