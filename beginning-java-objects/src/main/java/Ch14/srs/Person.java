package ch14.srs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {
	private String name;
	private String ssn;

	public Person() {
		this.setName("?");
		this.setSsn("???-??-????");
	}

	public void display() {
		System.out.println("Person Information:");
		System.out.println("\tName:  " + this.getName());
		System.out.println("\tSoc. Security No.:  " + this.getSsn());
	}
}
