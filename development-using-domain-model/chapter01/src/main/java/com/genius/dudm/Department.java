package com.genius.dudm;

public class Department {
	private long no;
	private String name;

	public Department(long no, String name) {
		this.no = no;
		this.name = name;
	}

	public Department() {
		this(0, "N/A");
	}

	public void setNo(int no) {
		this.no = no;
	}

	public void setName(String nane) {
		this.name = nane;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other == null || getClass() != other.getClass()) {
			return false;
		}
		Department department = (Department) other;
		return no == department.no && name == department.name;
	}
}
