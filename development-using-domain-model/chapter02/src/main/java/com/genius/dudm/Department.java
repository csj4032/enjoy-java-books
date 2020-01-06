package com.genius.dudm;

import java.util.Objects;

public class Department {
	private long no;
	private String name;
	private String address;

	public Department(long no, String name, String address) {
		this.no = no;
		this.name = name;
		this.address = address;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		if (other == null || getClass() != other.getClass()) return false;
		Department department = (Department) other;
		return no == department.no && name.equals(department.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(no);
	}
}
