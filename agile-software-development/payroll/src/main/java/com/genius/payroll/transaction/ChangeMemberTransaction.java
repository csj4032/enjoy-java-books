package com.genius.payroll.transaction;

import com.genius.payroll.Affiliation;
import com.genius.payroll.persistence.PayrollDatabase;
import com.genius.payroll.UnionAffiliation;
import com.genius.payroll.domain.Employee;

public class ChangeMemberTransaction extends ChangeAffiliationTransaction {

	private long memberId;
	private double dues;

	public ChangeMemberTransaction(long empId, long memberId, double dues) {
		super(empId);
		this.memberId = memberId;
		this.dues = dues;
	}

	@Override
	protected Affiliation getAffiliation() {
		return new UnionAffiliation(memberId, dues);
	}

	@Override
	protected void recordMembership(Employee employee) {
		PayrollDatabase.addUnionMember(memberId, employee);
	}
}