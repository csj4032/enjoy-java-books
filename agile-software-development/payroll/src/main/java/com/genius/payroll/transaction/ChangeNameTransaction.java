package com.genius.payroll.transaction;

import com.genius.payroll.domain.Employee;

public class ChangeNameTransaction extends ChangeEmployeeTransaction {

	private String name;

	public ChangeNameTransaction(long empId, String name) {
		super(empId);
		this.name = name;
	}

	@Override
	protected final void change(Employee employee) {
		employee.setName(name);
	}
}
