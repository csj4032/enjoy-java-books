package com.genius.payroll;

import lombok.ToString;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@ToString
public class PaydayTransaction implements Transaction {

	private LocalDate payDate;
	private Map<Long, Paycheck> paychecks = new HashMap();

	public PaydayTransaction(LocalDate payDate) {
		this.payDate = payDate;
	}

	@Override
	public void execute() {
		PayrollDatabase.getAllEmployeeIds().stream()
				.filter(e -> (PayrollDatabase.getEmployee(e).isPayDate(payDate)))
				.forEach(e -> {
					Employee employee = PayrollDatabase.getEmployee(e);
					Paycheck paycheck = new Paycheck(employee.getPayPeriodStartDate(payDate), payDate);
					paychecks.put(e, paycheck);
					employee.payday(paycheck);
				});
	}

	public Paycheck getPayCheck(long empId) {
		return paychecks.get(empId);
	}

	public int getPayCheckCount() {
		return paychecks.size();
	}
}