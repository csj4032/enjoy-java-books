package com.genius.dudm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class Employee extends DomainObject {
	private Long id;
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

	// 부서 이동을 수행하는 메서드
	public void setDepartment(Department department) {
		this.department = department;
	}

	public boolean isBelongTo(Department department) {
		return this.department.equals(department);
	}

	public String printForMove() {
		final String printMessage = "성명 : %s 부서 : %s 주소 : %s".formatted(name, department.getName(), department.getAddress());
		System.out.println(printMessage);
		return printMessage;
	}
}