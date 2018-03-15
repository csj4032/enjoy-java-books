package com.genius.payroll;

import org.junit.Test;

public class PayrollTest {

	@Test
	public void TestAddSalariedEmployee() {
		int empId = 1;
		AddEmployeeTransaction addEmployeeTransaction = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);
	}
}