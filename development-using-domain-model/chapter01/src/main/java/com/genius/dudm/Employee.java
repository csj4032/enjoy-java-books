package com.genius.dudm;

public class Employee {
	private long no;
	private String name;
	private String position;
	private Department department;

	public void setDepartment(Department department) {
		this.department = department;
	}

	public boolean isBelongsTo(Department department) {
		return this.department.equals(department);
	}
}