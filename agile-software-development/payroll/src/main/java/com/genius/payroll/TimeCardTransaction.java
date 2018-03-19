package com.genius.payroll;

import java.time.LocalDate;

public class TimeCardTransaction implements Transaction {

	private LocalDate date;
	private double hours;
	private long empId;

	public TimeCardTransaction(LocalDate date, double hours, long empId) {
		this.date = date;
		this.hours = hours;
		this.empId = empId;
	}

	@Override
	public void execute() throws InvalidEmployeeException {
		Employee employee = PayrollDatabase.getEmployee(empId);
		if (employee != null) {
			HourlyClassification hourlyClassification = (HourlyClassification) employee.getClassification();
			hourlyClassification.addTimeCard(new TimeCard(date, hours));
		} else {
			throw new InvalidEmployeeException("No such employee");
		}
	}
}
