package com.genius.payroll;

import java.time.LocalDate;

public class ServiceChargeTransaction implements Transaction {

	private long memberId;
	private LocalDate date;
	private double charge;

	public ServiceChargeTransaction(long memberId, LocalDate date, double charge) {
		this.memberId = memberId;
		this.date = date;
		this.charge = charge;
	}

	@Override
	public void execute() {
		Employee employee = PayrollDatabase.getUnionMember(memberId);
		UnionAffiliation unionAffiliation = (UnionAffiliation) employee.getAffiliation();
		unionAffiliation.addServiceCharge(date, charge);
	}
}
