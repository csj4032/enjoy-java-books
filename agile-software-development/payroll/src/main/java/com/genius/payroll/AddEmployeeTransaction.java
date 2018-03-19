package com.genius.payroll;

public abstract class AddEmployeeTransaction implements Transaction {

	private long empId;
	private String name;
	private String address;

	public AddEmployeeTransaction(long empId, String name, String address) {
		this.empId = empId;
		this.name = name;
		this.address = address;
	}

	abstract PaymentClassification getClassification();

	abstract PaymentSchedule getSchedule();

	@Override
	public void execute() {
		PaymentClassification paymentClassification = getClassification();
		PaymentSchedule paymentSchedule = getSchedule();
		PaymentMethod paymentMethod = new HoldMethod();
		Employee employee = new Employee(empId, name, address);
		employee.setClassification(paymentClassification);
		employee.setSchedule(paymentSchedule);
		employee.setPaymentMethod(paymentMethod);
		PayrollDatabase.addEmployee(empId, employee);
	}
}