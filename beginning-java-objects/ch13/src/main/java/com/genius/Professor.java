package com.genius;

import lombok.ToString;

@ToString
public class Professor extends Person {

	private String name;

	public Professor(String name) {
		this.name = name;
	}
}
