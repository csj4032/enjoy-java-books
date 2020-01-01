package com.genius.srs;

import org.junit.jupiter.api.*;

@DisplayName("Faculty 테스트")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FacultyTest {

	private static Faculty faculty;

	@BeforeAll
	public static void setUp() {
		faculty = new Faculty();
	}

	@Test
	@Order(1)
	@DisplayName("출력 테스트")
	void display() {
		boolean primary =  faculty.initializeObjects("data/Faculty.dat", true);
		Assertions.assertTrue(primary);
		boolean secondary =  faculty.initializeObjects("data/TeachingAssignments.dat", false);
		Assertions.assertTrue(secondary);
		faculty.display();
	}
}