package com.genius.generic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MaxEmployeesTest {

	@Test
	public void maxEmployee() {
		var maxEmployees = new MaxEmployees();
		var employee = maxEmployees.maxEmployee();
		Assertions.assertEquals(2, employee.getId());
	}
}