package com.genius.dudm.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(callSuper = true)
public class Department {
	private Long id;
	private String name;
	private String address;

	public Department(Long id, String name, String address) {
		this.id = id;
		this.name = name;
		this.address = address;
	}
}