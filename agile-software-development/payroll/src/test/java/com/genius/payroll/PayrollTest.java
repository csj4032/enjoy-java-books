package com.genius.payroll;

import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class PayrollTest {

	@Test
	@Ignore
	public void testAddSalariedEmployee() {
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

	@Test
	@Ignore
	public void testDeleteEmployee() {
		long empId = 3;
		AddCommissionedEmployee addCommissionedEmployee = new AddCommissionedEmployee(empId, "Lance", "Home", 2500.00, 3.2);
		addCommissionedEmployee.execute();

		Employee employee = PayrollDatabase.getEmployee(empId);
		assertThat(employee.getAddress(), is("Home"));

		DeleteEmployeeTransaction deleteEmployeeTransaction = new DeleteEmployeeTransaction(empId);
		deleteEmployeeTransaction.execute();
		assertNull(PayrollDatabase.getEmployee(3));
	}

	@Test
	public void testTimeCardTransaction() throws InvalidEmployeeException {
		long empId = 2;
		LocalDate date = LocalDate.of(2001, 12, 31);
		AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
		addHourlyEmployee.execute();

		TimeCardTransaction timeCardTransaction = new TimeCardTransaction(date, 8.0, empId);
		timeCardTransaction.execute();

		HourlyClassification hourlyClassification = (HourlyClassification) addHourlyEmployee.getClassification();
		TimeCard timeCard = hourlyClassification.getTimeCard(date);
		assertThat(8.0, is(timeCard.getHours()));
	}
}