package com.genius.dudm.domain;

import com.genius.dudm.service.EmployeeService;

import java.util.List;
import java.util.Objects;

public class Department extends DomainObject {
	private long id;
	private String name;
	private String address;
	private List<Employee> employees;

	public Department(long id, String name, String address, List<Employee> employees) {
		super(new DepartmentKey(id));
		this.id = id;
		this.name = name;
		this.address = address;
		this.employees = employees;
	}

	public boolean move(int limitsEmployeeCount, String newAddress) {
		EmployeeService employeeService = new EmployeeService();
		List<Employee> employees = employeeService.findAllEmployeeByDepartment(this);
		if (employees.size() < limitsEmployeeCount + 1) {
			this.setAddress(newAddress);
			employeeService.printForMove(employees);
			return true;
		}
		return false;
	}

	public long getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		if (other == null || getClass() != other.getClass()) return false;
		Department department = (Department) other;
		return id == department.id && name.equals(department.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}