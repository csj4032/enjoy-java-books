package com.genius.payroll;

public abstract class ChangeEmployeeTransaction implements Transaction {

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
