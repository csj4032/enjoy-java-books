package com.genius.generic;

import java.util.Comparator;
import java.util.List;

public class MaxEmployees {

	private static List<Employee> createEmployees() {
		return List.of(
				new Employee(1, "Pater Gibbons"),
				new Employee(2, "Samir Nagheenanagar")
		);
	}

	public Employee maxEmployee() {
		List<Employee> employees = createEmployees();
		return employees.stream().max(new Comparator<Employee>() {
			@Override
			public int compare(Employee e1, Employee e2) {
				return e1.getId() - e2.getId();
			}
		}).orElse(null);
	}
}
