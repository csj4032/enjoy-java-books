package com.genius;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Person {
	private String forename;
	private String surname;
	private LocalDate birthday;
	private Gender gender;
	private String emailAddress;
	private int age;
}
