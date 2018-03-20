package com.genius.payroll.transaction;

import com.genius.payroll.HourlyClassification;
import com.genius.payroll.PaymentClassification;
import com.genius.payroll.PaymentSchedule;
import com.genius.payroll.WeeklySchedule;
import com.genius.payroll.transaction.AddEmployeeTransaction;
import lombok.Getter;

@Getter
public class AddHourlyEmployee extends AddEmployeeTransaction {

	private double hourlyRate;

	public AddHourlyEmployee(long empId, String name, String address, double hourlyRate) {
		super(empId, name, address);
		this.hourlyRate = hourlyRate;
	}

	@Override
	public PaymentClassification getClassification() {
		return new HourlyClassification(hourlyRate);
	}

	@Override
	public PaymentSchedule getSchedule() {
		return new WeeklySchedule();
	}
}
