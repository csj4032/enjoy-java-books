package com.genius.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AdvancedTest {

	private static List<Student> students = new ArrayList();

	@BeforeAll
	public static void setUp() {
		Student s1 = Student.builder().id(1).name("강길동").grade(Grade.Freshman).point(100).build();
		Student s2 = Student.builder().id(2).name("김길동").grade(Grade.Freshman).point(99).build();
		Student s3 = Student.builder().id(3).name("나길동").grade(Grade.Freshman).point(98).build();
		students.add(s1);
		students.add(s2);
		students.add(s3);
	}

	@Test
	@Order(1)
	public void Top1ByPoints() {
		Student top1Student = new Student();
		for (Student student : students) {
			if (top1Student.getPoint() < student.getPoint())
				top1Student = student;
		}
		Assertions.assertEquals("강길동", top1Student.getName());
	}
}
