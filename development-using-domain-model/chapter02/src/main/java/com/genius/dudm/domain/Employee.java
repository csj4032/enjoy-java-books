package com.genius.dudm.domain;

import java.util.Objects;

public class Employee extends DomainObject {
	private long id;
	private String name;
	private String position;

	public Employee(Long id, String name, String position) {
		super(new EmployeeKey(id));
		this.id = id;
		this.name = name;
		this.position = position;
	}

	public Employee() {
		this(0L, "Anonymous", "N/A");
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
		System.out.printf("성명 : %s  부서 : %s  주소 : %s\n", name);
	}
}