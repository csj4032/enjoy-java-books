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

		//List<B> as = Stream.of(new A()).peek((A a) -> new B()).collect(Collectors.toUnmodifiableList());
		List<B> bs = Stream.of(new B()).peek((A b) -> ((B) b).getAge()).collect(Collectors.toUnmodifiableList());
	}
}

@Data
class A {
	String name;
}

@Data
class B extends A {
	String age;
}
