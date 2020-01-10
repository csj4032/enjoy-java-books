package com.genius.dudm.domain;

import java.util.Objects;

public class Employee extends DomainObject {
	private long no;
	private String name;
	private String position;
	private Department department;

	public Employee(Long no, String name, String position, Department department) {
		super(new EmployeeKey(no));
		this.no = no;
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

	public long getNo() {
		return no;
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
		return no == employee.no;
	}

	@Override
	public int hashCode() {
		return Objects.hash(no);
	}

	public void printForMove() {
		System.out.printf("성명 : %s  부서 : %s  주소 : %s\n", name, department.getName(), department.getAddress());
	}
}