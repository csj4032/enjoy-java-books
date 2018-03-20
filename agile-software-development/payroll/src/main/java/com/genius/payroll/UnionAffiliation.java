package com.genius.payroll;

import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class UnionAffiliation implements Affiliation {

	@Getter
	private long memberId;
	@Getter
	private double dues;
	private Map<LocalDate, ServiceCharge> serviceCharges = new HashMap<>();

	public UnionAffiliation(long memberId, double dues) {
		this.memberId = memberId;
		this.dues = dues;
	}

	public UnionAffiliation(double dues) {
		this.dues = dues;
	}

	public ServiceCharge getServiceCharge(LocalDate date) {
		return serviceCharges.get(date);
	}

	@Override
	public double calculateDeductions(Paycheck paycheck) {
		double totalServiceCharge = serviceCharges.values().stream()
				.filter(e -> paycheck.getPayPeriodStartDate().minusDays(1).isBefore(e.getDate()))
				.filter(e -> paycheck.getPayPeriodEndDate().plusDays(1).isAfter(e.getDate()))
				.mapToDouble(e -> e.getAmount())
				.sum();
		double totalDues = (dues * numberOfFridaysInPayPeriod(paycheck.getPayPeriodStartDate(), paycheck.getPayPeriodEndDate()));
		return totalDues + totalServiceCharge;
	}

	private double numberOfFridaysInPayPeriod(LocalDate payPeriodStartDate, LocalDate payPeriodEndDate) {
		double fridays = 0;
		for (LocalDate date = payPeriodStartDate; date.isBefore(payPeriodEndDate.plusDays(1)); date = date.plusDays(1)) {
			if (date.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
				fridays++;
			}
		}
		return fridays;
	}

	public void addServiceCharge(LocalDate date, double amount) {
		ServiceCharge serviceCharge = new ServiceCharge(date, amount);
		serviceCharges.put(date, serviceCharge);
	}
}
