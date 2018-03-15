package com.genius.payroll;

import java.util.Date;
import java.util.Map;

public class Paycheck {

	private Date itsPayPeriodStartDate;
	private Date itsPayPeriodEndDate;
	private double itsGrossPay;
	private double itsNetPay;
	private double itsDeductions;
	private Map<String, String> itsFields;
}
