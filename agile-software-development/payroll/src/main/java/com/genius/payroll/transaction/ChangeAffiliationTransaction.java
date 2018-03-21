package com.genius.payroll.transaction;

import com.genius.payroll.Affiliation;
import com.genius.payroll.domain.Employee;

public abstract class ChangeAffiliationTransaction extends ChangeEmployeeTransaction {

	public ChangeAffiliationTransaction(long empId) {
		super(empId);
	}

	@Override
	protected void change(Employee employee) {
		recordMembership(employee);
		employee.setAffiliation(getAffiliation());
	}

	abstract void recordMembership(Employee employee);

	abstract Affiliation getAffiliation();
}
