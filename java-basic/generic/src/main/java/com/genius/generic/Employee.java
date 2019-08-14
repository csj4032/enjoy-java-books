
package com.genius.generic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee extends Person {

	private final int id;

	public Employee(int id, String name) {
		super(name);
		this.id = id;
	}
}