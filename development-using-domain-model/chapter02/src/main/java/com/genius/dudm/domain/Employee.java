package com.genius.dudm.domain;

import java.util.Objects;

public class Employee {
	private long no;
	private String name;
	private String position;
	private Department department;

	public Employee(long no, String name, String position, Department department) {
		this.no = no;
		this.name = name;
		this.position = position;
		this.department = department;
	}

	public boolean isBelongsTo(Department department) {
		return this.department.equals(department);
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		if (other == null || getClass() != other.getClass()) return false;
		Employee employee = (Employee) other;
		return no == employee.no;
	}

	@Override
	public int hashCode() {
		return Objects.hash(no);
	}
}