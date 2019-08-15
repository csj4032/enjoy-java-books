package com.genius.generic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MaxEmployeesTest {

	private static List<Employee> employees = new ArrayList<>();

	@BeforeAll
	public static void createEmployees() {
		employees.add(new Employee(LocalDate.now(), "Pater Gibbons", 1));
		employees.add(new Employee(LocalDate.now(), "Samir Nagheenanajar", 2));
		employees.add(new Employee(LocalDate.now(), "Michael Bolton", 3));
	}

	@Test
	public void maxIdTest() {
		var maxEmployees = new MaxEmployees();
		var employee = maxEmployees.maxId(employees);
		Assertions.assertEquals(3, employee.getId());
	}

	@Test
	public void maxNameTest() {
		var maxEmployees = new MaxEmployees();
		var employee = maxEmployees.maxName(employees);
		Assertions.assertEquals("Michael Bolton", employee.getName());
	}
}