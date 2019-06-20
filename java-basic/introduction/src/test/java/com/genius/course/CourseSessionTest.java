package com.genius.course;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class CourseSessionTest {

	private static CourseSession session;
	private static LocalDate startDate;

	@BeforeAll
	public static void setUp() {
		session = new CourseSession("ENGL", "101");
		startDate = LocalDate.of(2019, 3, 2);
	}

	@Test
	public void testCreate() {
		Assertions.assertEquals("ENGL", session.getDepartment());
		Assertions.assertEquals("101", session.getNumber());
	}

	@Test
	public void testEnrollStudents() {

		Assertions.assertEquals(0, session.getNumberOfStudents());

		Student student = new Student("genius");
		session.enroll(student);

		List<Student> allStudents = session.getAllStudents();
		Assertions.assertEquals(1, allStudents.size());
		Assertions.assertEquals(1, session.getNumberOfStudents());

		Student student2 = new Student("genius2");
		session.enroll(student2);

		Assertions.assertEquals(2, session.getNumberOfStudents());
		Assertions.assertEquals(2, allStudents.size());
		Assertions.assertEquals(2, session.getNumberOfStudents());


		Assertions.assertEquals(student, allStudents.get(0));
		Assertions.assertEquals(student2, allStudents.get(1));

		Assertions.assertEquals(student, session.get(0));
		Assertions.assertEquals(student2, session.get(1));
	}

	@Test
	public void testCourseDate() {
		CourseSession session = new CourseSession("ABCD", "102", startDate);
		Assertions.assertEquals(startDate.plusDays(109), session.getEndDate());
	}
}