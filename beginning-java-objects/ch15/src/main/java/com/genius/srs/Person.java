package com.genius.srs;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public abstract class Person {
	private String name;
	private String ssn;

	public Person(String name, String ssn) {
		setName(name);
		setSsn(ssn);
	}

	public void display() {
		log.info("Person Information:");
		log.info("\tName:  " + getName());
		log.info("\tSoc. Security No.:  " + getSsn());
	}
}