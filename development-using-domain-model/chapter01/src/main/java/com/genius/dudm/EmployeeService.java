package com.genius.dudm;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class EmployeeService {

	private final List<Employee> employees;

	{
		Department department = new Department(1, "총무과");
		Department department2 = new Department(2, "경리과");

		Employee employee = new Employee();
		employee.setDepartment(department);

		Employee employee2 = new Employee();
		employee2.setDepartment(department);

		Employee employee3 = new Employee();
		employee3.setDepartment(department);

		Employee employee4 = new Employee();
		employee4.setDepartment(department);

		Employee employee5 = new Employee();
		employee5.setDepartment(department2);

		employees = List.of(employee, employee2, employee3, employee4, employee5);
	}

	public List<Employee> findAllEmployee() {
		return employees;
	}

	public List<Employee> findAllEmployeeByDepartment(Department department) {
		return findAllEmployee().stream().filter(e -> e.isBelongsTo(department)).collect(toList());
	}
}
