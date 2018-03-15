package com.genius.payroll;

public abstract class AddEmployeeTransaction implements Transaction {

	private int itsEmpId;
	private String itsName;
	private String itsAddress;

	public AddEmployeeTransaction(int itsEmpId, String itsName, String itsAddress) {
		this.itsEmpId = itsEmpId;
		this.itsName = itsName;
		this.itsAddress = itsAddress;
	}

	abstract PaymentClassification getClassification();

	abstract PaymentSchedule getSchedule();

	@Override
	public void execute() {
		PaymentClassification paymentClassification = getClassification();
		PaymentSchedule paymentSchedule = getSchedule();
		Employee employee = new Employee(itsEmpId, itsName, itsAddress);
		employee.setClassification(paymentClassification);
		employee.setSchedule(paymentSchedule);
		PayrollDatabase.AddEmployee(itsEmpId, employee);
	}
}