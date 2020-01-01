package com.genius.srs;

import org.junit.jupiter.api.*;

@DisplayName("ScheduleOfClasses 테스트")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ScheduleOfClassesTest {

	private static ScheduleOfClasses scheduleOfClasses;

	@BeforeAll
	public static void setUp() {
		scheduleOfClasses = new ScheduleOfClasses("SP2001");
	}

	@Test
	@Order(1)
	@DisplayName("출력 테스트")
	void display() {
		scheduleOfClasses.display();
	}
}