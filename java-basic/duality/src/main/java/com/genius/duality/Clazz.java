package com.genius.duality;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Clazz {

	private String name;
	private List<Student> students;
	private int totalPoint;

	public Clazz() {
	}

	public Clazz(String name, List<Student> students) {
		this.name = name;
		this.students = students;
	}

	public int getTotalPoint() {
		return students.stream().mapToInt(e -> e.getPoint()).sum();
	}
}
