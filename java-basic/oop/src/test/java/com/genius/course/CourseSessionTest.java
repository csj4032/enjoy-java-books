package com.genius.course;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CourseSessionTest {

	private static CourseSession session;
	private static LocalDate startDate;
	private static final int CREDITS = 3;

	@BeforeAll
	public static void setUp() {
		startDate = LocalDate.of(2019, 3, 2);
		session = createCourseSession();
	}

	@Test
	@Order(1)
	public void testCreate() {
		assertEquals("ENGL", session.getDepartment());
		assertEquals("101", session.getNumber());
		assertEquals(0, session.getNumberOfStudents());
		assertEquals(startDate, session.getStartDate());
	}

	@Test
	@Order(2)
	public void testEnrollStudents() {

		assertEquals(0, session.getNumberOfStudents());

		Student student = new Student("genius");
		session.enroll(student);

		List<Student> allStudents = session.getAllStudents();
		assertEquals(1, allStudents.size());
		assertEquals(1, session.getNumberOfStudents());

		Student student2 = new Student("genius2");
		session.enroll(student2);

		assertEquals(2, session.getNumberOfStudents());
		assertEquals(2, allStudents.size());
		assertEquals(2, session.getNumberOfStudents());


		assertEquals(student, allStudents.get(0));
		assertEquals(student2, allStudents.get(1));

		assertEquals(student, session.get(0));
		assertEquals(student2, session.get(1));

		assertEquals(3, student.getCredits());
	}

	@Test
	@Order(3)
	public void testCourseDate() {
		CourseSession session = new CourseSession("ABCD", "102", startDate);
		assertEquals(startDate.plusDays(109), session.getEndDate());
	}

	@Test
	@Order(4)
	public void testCount() {
		CourseSession.resetCount();
		createCourseSession();
		CourseSession.incrementCount();
		assertEquals(1, CourseSession.getCount());
		createCourseSession();
		CourseSession.incrementCount();
		assertEquals(2, CourseSession.getCount());
	}

	private static CourseSession createCourseSession() {
		CourseSession session = CourseSession.of("ENGL", "101", startDate);
		session.setNumberOfCredits(CourseSessionTest.CREDITS);
		return session;
	}
}
