package com.genius.collection;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Grouping {

	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
		Set<Object> set = ConcurrentHashMap.newKeySet();
		return t -> set.add(keyExtractor.apply(t));
	}

	public static void main(String[] args) {
		List<Student> students = List.of(
				new Student(1, "A", Grade.Freshman, 100),
				new Student(2, "B", Grade.Freshman, 100),
				new Student(3, "C", Grade.Junior, 100)
		);
		List<Student> studentsByGrade = students.stream().filter(distinctByKey(Student::getGrade)).collect(Collectors.toList());
		System.out.println(studentsByGrade);
	}
}