package com.genius;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConstructorReference {

	private String forename = "";
	private String surname;
	private LocalDate birthday;
	private Gender gender;
	private String email;
	private int age;

	public static void main(String[] args) {
		ConstructorReference constructorReference = new ConstructorReference();
		constructorReference.usage();
	}

	public void usage() {
		List<String> list = new ArrayList<>();
		initialise(list, String::new);
		System.out.println(list);

		List<Person> list2 = new ArrayList<>();
		initialise2(list2, Person::new);
		System.out.println(list2);

		List<Person> list3 = new ArrayList<>();
		initialise3(list3, Person::new, forename, surname, birthday, gender, email, age);
		System.out.println(list3);
	}

	private <T> void initialise(List<T> list, Factory<T> factory) {
		for (int i = 0; i < 10; i++) {
			list.add(factory.create());
		}
	}

	private void example() {
		PersonFactoryNo factoryNo = () -> new Person();
		PersonFactoryAll factoryAll = (a, b, c, d, e, f) -> new Person(a, b, c, d, e, f);
		Person personNo = factoryNo.create();
		Person personAll = factoryAll.create(forename, surname, birthday, gender, email, age);
	}

	private void initialise2(List<Person> list, PersonFactoryNo factory) {
		for (int i = 0; i < 10; i++) {
			list.add(factory.create());
		}
	}

	private void initialise3(List<Person> list, PersonFactoryAll factory, String forename, String surname, LocalDate birthday, Gender gender, String email, int age) {
		for (int i = 0; i < 10; i++) {
			list.add(factory.create(forename, surname, birthday, gender, email, age));
		}
	}

	@FunctionalInterface
	interface Factory<T> {
		T create();
	}

	@FunctionalInterface
	interface PersonFactoryNo {
		Person create();
	}

	@FunctionalInterface
	interface PersonFactoryAll {
		Person create(String forename, String surname, LocalDate birthday, Gender gender, String emailAddress, int age);
	}
}

