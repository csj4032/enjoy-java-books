package com.genius.payroll;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Employee {
	private long empId;
	private String name;
	private String address;
	private PaymentClassification classification;
	private PaymentSchedule schedule;
	private PaymentMethod paymentMethod;
	private Affiliation affiliation;

	public Employee(long empId, String name, String address) {
		this.empId = empId;
		this.name = name;
		this.address = address;
		this.affiliation = new NoAffiliation();
		this.classification = null;
		this.schedule = null;
		this.paymentMethod = null;
	}

	boolean isPayDate(Date payDate) {
		return false;
	}

	public void payday(Paycheck paycheck) {

	}
}