package com.genius.dudm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class Employee {
	private Long id;
	private String name;
	private String position;
	private Department department;

	public Employee(Long id, String name, String position) {
		this.id = id;
		this.name = name;
		this.position = position;
	}

	public boolean isBelongTo(Department department) {
		return this.department.equals(department);
	}
}