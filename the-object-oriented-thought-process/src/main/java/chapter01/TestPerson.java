package chapter01;

import lombok.Data;

public class TestPerson {

	public static void main(String[] args) {
		Person joe = new Person();
		joe.setName("Joe");
		System.out.println(joe.getName());
	}
}

@Data
class Person {
	private String name;
	private String address;
}