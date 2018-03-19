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
	@Ignore
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

	@Test
	@Ignore
	public void testAddServiceCharge() {
		long empId = 2;
		AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
		addHourlyEmployee.execute();

		Employee employee = PayrollDatabase.getEmployee(empId);
		assertThat("Bill", is(employee.getName()));

		UnionAffiliation unionAffiliation = new UnionAffiliation(12.5);
		employee.setAffiliation(unionAffiliation);

		long memberId = 86l;
		LocalDate date = LocalDate.of(2001, 12, 1);
		PayrollDatabase.addUnionMember(memberId, employee);

		ServiceChargeTransaction serviceChargeTransaction = new ServiceChargeTransaction(memberId, date, 12.95);
		serviceChargeTransaction.execute();

		ServiceCharge serviceCharge = unionAffiliation.getServiceCharge(date);

		assertThat(12.95, is(serviceCharge.getAmount()));
	}

	@Test
	@Ignore
	public void testChangeNameTransaction() {
		long empId = 2;
		AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
		addHourlyEmployee.execute();

		ChangeNameTransaction changeNameTransaction = new ChangeNameTransaction(empId, "Bob");
		changeNameTransaction.execute();

		Employee employee = PayrollDatabase.getEmployee(empId);
		assertThat("Bob", is(employee.getName()));
	}

	@Test
	public void testChangeHourlyTransaction() {
		long empId = 4l;
		AddCommissionedEmployee addCommissionedEmployee = new AddCommissionedEmployee(empId, "Lance", "Home", 2500, 3.2);
		addCommissionedEmployee.execute();

		ChangeHourlyTransaction changeHourlyTransaction;
	}
}