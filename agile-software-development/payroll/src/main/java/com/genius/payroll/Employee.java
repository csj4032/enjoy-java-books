package com.genius.payroll;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
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

	boolean isPayDate(LocalDate payDate) {
		return schedule.isPayDate(payDate);
	}

	public LocalDate getPayPeriodStartDate(LocalDate payPeriodEndDate) {
		return schedule.getPayPeriodStartDate(payPeriodEndDate);
	}

	public void payday(Paycheck paycheck) {
		double grossPay = classification.calculatePay(paycheck);
		double deductions = affiliation.calculateDeductions(paycheck);
		double netPay = grossPay - deductions;
		paycheck.setGrossPay(grossPay);
		paycheck.setDeductions(deductions);
		paycheck.setNetPay(netPay);
		paymentMethod.pay(paycheck);
	}
}