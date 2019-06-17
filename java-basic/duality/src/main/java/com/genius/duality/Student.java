package com.genius.duality;

import lombok.Getter;

@Getter
public class Student {

	private String id;
	private Integer point;

	public Student(String id, Integer point) {
		this.id = id;
		this.point = point;
	}

}
