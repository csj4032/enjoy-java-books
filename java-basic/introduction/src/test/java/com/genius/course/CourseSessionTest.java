package com.genius.course;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CourseSessionTest {

	private static CourseSession session;
	private static LocalDate startDate;

	@BeforeAll
	public static void setUp() {
		session = new CourseSession("ENGL", "101");
		startDate = LocalDate.of(2019, 3, 2);
	}

	@Test
	@Order(1)
	public void testCreate() {
		Assertions.assertEquals("ENGL", session.getDepartment());
		Assertions.assertEquals("101", session.getNumber());
	}

	@Test
	@Order(2)
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
	@Order(3)
	public void testCourseDate() {
		CourseSession session = new CourseSession("ABCD", "102", startDate);
		Assertions.assertEquals(startDate.plusDays(109), session.getEndDate());
	}

	@Test
	@Order(4)
	public void testRosterReport() {
		session = new CourseSession("ENGL", "101");
		session.enroll(new Student("A"));
		session.enroll(new Student("B"));

		String rosterReport = session.getRosterReport();
		Assertions.assertEquals(CourseSession.ROSTER_REPORT_HEADER + "A\nB\n" + CourseSession.ROSTER_REPORT_FOOT + "2\n" , rosterReport);
	}
}
