package com.genius.payroll.transaction;

import com.genius.payroll.BiweeklySchedule;
import com.genius.payroll.CommissionedClassification;
import com.genius.payroll.PaymentClassification;
import com.genius.payroll.PaymentSchedule;
import com.genius.payroll.transaction.AddEmployeeTransaction;

public class AddCommissionedEmployee extends AddEmployeeTransaction {

	private final double salary;
	private final double commissionRate;
	private PaymentClassification paymentClassification;

	public AddCommissionedEmployee(long empId, String name, String address, double salary, double commissionRate) {
		super(empId, name, address);
		this.salary = salary;
		this.commissionRate = commissionRate;
	}

	@Override
	protected PaymentClassification getClassification() {
		return new CommissionedClassification(salary, commissionRate);
	}

	@Override
	protected PaymentSchedule getSchedule() {
		return new BiweeklySchedule();
	}
}
