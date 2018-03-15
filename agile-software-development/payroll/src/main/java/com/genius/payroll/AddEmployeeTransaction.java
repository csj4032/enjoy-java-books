package com.genius.payroll;

public abstract class AddEmployeeTransaction implements Transction {

	private int itsEmpId;
	private String itsName;
	private String itsAddress;

	public AddEmployeeTransaction(int itsEmpId, String itsName, String itsAddress) {
		this.itsEmpId = itsEmpId;
		this.itsName = itsName;
		this.itsAddress = itsAddress;
	}

	@Override
	public void transaction() {

	}

	@Override
	public void execute() {

	}
}