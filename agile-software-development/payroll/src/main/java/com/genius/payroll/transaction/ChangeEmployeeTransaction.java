package com.genius.payroll.transaction;

import com.genius.payroll.persistence.PayrollDatabase;
import com.genius.payroll.domain.Employee;
import lombok.Getter;

public abstract class ChangeEmployeeTransaction implements Transaction {

	@Getter
	private long empId;

	public ChangeEmployeeTransaction(long empId) {
		this.empId = empId;
	}

	public void execute() {
		Employee employee = PayrollDatabase.getEmployee(empId);
		if (employee != null) change(employee);
	}

	protected abstract void change(Employee employee);

}