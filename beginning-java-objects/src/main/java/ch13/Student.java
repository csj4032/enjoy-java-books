package ch13;

import lombok.ToString;

@ToString
public class Student extends Person {

	private String name;

	public Student(String name) {
		this.name = name;
	}
}