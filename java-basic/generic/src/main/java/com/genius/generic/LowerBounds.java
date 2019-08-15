package com.genius.generic;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class LowerBounds {

	public void lowerBounds() {
		List<String> strings = Stream.of("a", "few", "strings").collect(toList());
		strings.forEach((String s) -> System.out.printf("%s in all caps is %s%n", s, s.toUpperCase()));
		strings.forEach((Object o) -> System.out.printf("%s in all caps is %s%n", o, o.hashCode()));

		//List<Employee> employees = Stream.of(new Person()).peek((Person p) -> new Employee()).collect(Collectors.toUnmodifiableList());
		//List<Employee> employees = Stream.of(new Employee()).peek((Person p) -> ((Employee) p).getID()).collect(Collectors.toUnmodifiableList());
	}
}