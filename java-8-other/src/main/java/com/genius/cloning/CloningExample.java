package com.genius.cloning;

/**
 * https://dzone.com/articles/java-cloning-copy-constructor-vs-cloning?edition=306229&utm_source=Daily%20Digest&utm_medium=email&utm_campaign=dd%202017-07-05
 */
public class CloningExample {
	public static void main(String[] args) throws CloneNotSupportedException {

		City city = new City("Dehradun");
		Person person1 = new Person("Naresh", 10000, city);

		System.out.println(person1);
		Person person2 = person1.clone();
		System.out.println(person2);

		if (person1 == person2) {
			System.out.println("Both person1 and person2 holds same object");
		}

		if (person1.equals(person2)) {
			System.out.println("But both person1 and person2 are equal and have same content");
		}

		if (person1.getCity() == person2.getCity()) {
			System.out.println("Both person1 and person2 have same city object");
		}

		if (person1.getCity().equals(person2.getCity())) {
			System.out.println("Both person1 and person2 have same city object");
		}
	}
}