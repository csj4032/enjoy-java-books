package chapter07.item43;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Person {

	public enum Sex {
		MALE, FEMALE
	}

	public Person(String name, LocalDate birthday) {
		this.name = name;
		this.birthday = birthday;
	}

	String name;
	LocalDate birthday;
	Sex gender;
	String emailAddress;

	public String getName() {
		return name;
	}

	public int getAge() {
		return LocalDate.now().getYear() - birthday.getYear();
	}

	public Calendar getBirthday() {
		return new GregorianCalendar();
	}

	public static int compareByAge(Person a, Person b) {
		return a.birthday.compareTo(b.birthday);
	}
}