package com.genius.payroll;

import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class PayrollTest {

	@Test
	public void TestAddSalariedEmployee() {
		long empId = 1L;
		AddEmployeeTransaction addEmployeeTransaction = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);
		addEmployeeTransaction.execute();

		Employee employee = PayrollDatabase.getEmployee(empId);
		assertThat("Bob", is(employee.getName()));

		PaymentClassification paymentClassification = employee.getClassification();
		SalariedClassification salariedClassification = (SalariedClassification) paymentClassification;
		assertThat(1000.00, is(salariedClassification.getSalary()));

		PaymentSchedule paymentSchedule = employee.getSchedule();
		MonthlySchedule monthlySchedule = (MonthlySchedule) paymentSchedule;
		assertNotNull(monthlySchedule);

		PaymentMethod paymentMethod = employee.getPaymentMethod();
		HoldMethod holdMethod = (HoldMethod) paymentMethod;
		assertNotNull(holdMethod);
	}
}