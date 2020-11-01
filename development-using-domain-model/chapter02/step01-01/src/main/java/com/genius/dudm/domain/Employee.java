package com.genius.dudm.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(callSuper = true)
public class Employee {
	private Long id;
	private String name;
	private String position;
	private Department department;

	public Employee(Long id, String name, String position, Department department) {
		this.id = id;
		this.name = name;
		this.position = position;
		this.department = department;
	}

	// 부서 이동을 수행하는 메서드
	public void setDepartment(Department department) {
		this.department = department;
	}

	public boolean isBelongTo(Department department) {
		return this.department.equals(department);
	}
}