package ch06;

import lombok.Data;

@Data
public class Student {
	private String idNo;
	private String name;

	public Student(String name) {
		this.name = name;
	}
}
