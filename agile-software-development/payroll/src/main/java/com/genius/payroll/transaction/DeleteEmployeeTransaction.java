package com.genius.payroll.transaction;

import com.genius.payroll.persistence.PayrollDatabase;
import com.genius.payroll.Transaction;

public class DeleteEmployeeTransaction implements Transaction {

	private final long empId;

	public DeleteEmployeeTransaction(long empId) {
		this.empId = empId;
	}

	@Override
	public void execute() {
		PayrollDatabase.deleteEmployee(empId);
	}
}