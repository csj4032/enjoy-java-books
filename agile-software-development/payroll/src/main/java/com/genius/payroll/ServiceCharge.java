package com.genius.payroll;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ServiceCharge {

	private LocalDate date;
	private double amount;

	public ServiceCharge(LocalDate date, double amount) {
		this.date = date;
		this.amount = amount;
	}
}
