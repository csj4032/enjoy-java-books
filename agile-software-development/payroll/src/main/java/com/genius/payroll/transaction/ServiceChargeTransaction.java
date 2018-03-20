package com.genius.payroll.transaction;

import com.genius.payroll.persistence.PayrollDatabase;
import com.genius.payroll.Transaction;
import com.genius.payroll.UnionAffiliation;
import com.genius.payroll.domain.Employee;

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
