package com.genius.payroll;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class ServiceCharge {

	private LocalDate date;
	private double amount;

	public ServiceCharge(LocalDate date, double amount) {
		this.date = date;
		this.amount = amount;
	}
}
