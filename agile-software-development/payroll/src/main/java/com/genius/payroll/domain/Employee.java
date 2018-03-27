package com.genius.payroll.domain;

import com.genius.payroll.*;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EMPLOYEE")
public class Employee {

	@Id
	//@GeneratedValue
	@Column(name = "ID")
	private Long empId;
	@Column(name = "NAME")
	private String name;
	@Column(name = "ADDRESS")
	private String address;
	@Transient
	private PaymentClassification classification;
	@Transient
	private PaymentSchedule schedule;
	@Transient
	private PaymentMethod paymentMethod;
	@Transient
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

	public boolean isPayDate(LocalDate payDate) {
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