
package com.genius.generic;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString(callSuper = true)
public class Employee extends Person implements Comparable<Employee> {

	public static final  Employee DEFAULT_EMPLOYEE = new Employee();
	private static final Integer IDENTITY = 0;
	private Integer id;

	public Employee() {
		this.id = IDENTITY;
	}

	public Employee(LocalDate birth, String name, int id) {
		super(birth, name);
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public int compareTo(Employee o) {
		return this.getId().compareTo(o.getId());
	}
}