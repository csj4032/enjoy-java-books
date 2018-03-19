package com.genius.payroll;

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