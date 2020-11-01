package com.genius.dudm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class Department extends DomainObject {
	private Long id;
	private String name;
	private String address;

	public Department(Long id, String name, String address) {
		super(new DepartmentKey(id));
		this.id = id;
		this.name = name;
		this.address = address;
	}
}