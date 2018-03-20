package com.genius.payroll;

import lombok.Getter;

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

	public UnionAffiliation(double v) {

	}

	public ServiceCharge getServiceCharge(LocalDate date) {
		return serviceCharges.get(date);
	}

	@Override
	public double calculateDeductions(Paycheck paycheck) {
		return 0;
	}

	public void addServiceCharge(LocalDate date, double amount) {
		ServiceCharge serviceCharge = new ServiceCharge(date, amount);
		serviceCharges.put(date, serviceCharge);
	}
}
