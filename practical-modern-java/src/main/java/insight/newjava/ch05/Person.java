package insight.newjava.ch05;

import com.sun.source.tree.BreakTree;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Function;

public class Person implements Comparable<Person> {

	String name;

	int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Person person = (Person) o;
		return age == person.age &&
				Objects.equals(name, person.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, age);
	}

	@Override
	public int compareTo(@NotNull Person o) {
		return (name + age).compareTo(o.getName() + o.getAge());
	}

	@Override
	public String toString() {
		return "Person{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
	}

	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(-2);
		System.out.println(numbers.stream().anyMatch(number -> (number & 1) == 0));
		System.out.println(numbers.stream().anyMatch(number -> number % 2 == 0));

		System.out.println(numbers.stream().noneMatch(number -> (number & 0) == 0));
		System.out.println(numbers.stream().noneMatch(number -> number % 2 == 0));
	}
}
