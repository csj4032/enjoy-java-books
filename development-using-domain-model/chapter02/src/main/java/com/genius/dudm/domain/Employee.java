package com.genius.dudm.domain;

import java.util.Objects;

public class Employee extends DomainObject {
	private long id;
	private String name;
	private String position;
	private Department department;

	public Employee(Long id, String name, String position, Department department) {
		super(new EmployeeKey(id));
		this.id = id;
		this.name = name;
		this.position = position;
		this.department = department;
	}

	public Employee() {
		this(0L, "Anonymous", "N/A", null);
	}

	public boolean isBelongsTo(Department department) {
		return this.department.equals(department);
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPosition() {
		return position;
	}

	public Department getDepartment() {
		return department;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		if (other == null || getClass() != other.getClass()) return false;
		Employee employee = (Employee) other;
		return id == employee.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public void printForMove() {
		System.out.printf("성명 : %s  부서 : %s  주소 : %s\n", name, department.getName(), department.getAddress());
	}
}