package com.genius;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

	public String name;
	public int age;

	public double dogYears() {
		return age / 7.0;
	}
}
