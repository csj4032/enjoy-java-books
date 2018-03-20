package com.genius.payroll;

public class ChangeMemberTransaction extends ChangeAffiliationTransaction {

	private long memberId;
	private double dues;

	public ChangeMemberTransaction(long empId, int memberId, double dues) {
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