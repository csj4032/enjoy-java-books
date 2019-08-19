package com.genius.generic;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
@ToString
public class Person {

	private static String UNKNOWN = "unknown";
	private static LocalDate NOW = LocalDate.now();



	private final LocalDate birth;
	private final String name;

	public Person() {
		this.birth = NOW;
		this.name = UNKNOWN;
	}

	public Person(LocalDate birth, String name) {
		this.birth = birth;
		this.name = name;
	}

	public Integer getNumber() {
		return ThreadLocalRandom.current().nextInt(100000);
	}
}