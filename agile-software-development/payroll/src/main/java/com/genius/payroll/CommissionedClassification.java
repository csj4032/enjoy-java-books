package com.genius.payroll;

import lombok.Getter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Getter
public class CommissionedClassification implements PaymentClassification {

	private final double salary;
	private final double commissionRate;
	private Map<LocalDate, SalesReceipt> receipts = new HashMap<>();

	public CommissionedClassification(double salary, double commissionRate) {
		this.salary = salary;
		this.commissionRate = commissionRate;
	}

	@Override
	public double calculatePay(Paycheck paycheck) {
		return salary + receipts.values().stream()
				.filter(e -> (paycheck.getPayPeriodStartDate().isAfter(e.getSaleDate())))
				.filter(e -> (paycheck.getPayPeriodEndDate().isBefore(e.getSaleDate())))
				.mapToDouble(e -> e.getAmount() * commissionRate)
				.sum();
	}

	public SalesReceipt getReceipt(LocalDate localDate) {
		return receipts.get(localDate);
	}

	public void addReceipt(SalesReceipt salesReceipt) {
		receipts.put(salesReceipt.getSaleDate(), salesReceipt);
	}
}
