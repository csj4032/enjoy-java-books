package com.genius.srs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Person {
	private String name;
	private String ssn;

	public Person(String name, String ssn) {
		setName(name);
		setSsn(ssn);
	}

	public Person() {
		setName("?");
		setSsn("???-??-????");
	}

	public abstract String toString();

	public void display() {
		System.out.println("Person Information:");
		System.out.println("\tName:  " + getName());
		System.out.println("\tSoc. Security No.:  " + getSsn());
	}
}