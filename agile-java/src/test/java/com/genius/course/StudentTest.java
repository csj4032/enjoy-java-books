package com.genius.course;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StudentTest {

	@Test
	@DisplayName("생성 테스트")
	public void create() {
		Student student = new Student("genius");
		Assertions.assertNotNull(student);

		var secondStudentName = "Spring";
		var secondStudent = new Student(secondStudentName);
		Assertions.assertEquals(secondStudentName, secondStudent.getName());
	}

	@Test
	@DisplayName("이름 반환 테스트")
	public void getNameTest() {
		Assertions.assertEquals("genius", new Student("genius").getName());
		Assertions.assertEquals("홍길동", new Student("홍길동").getName());
	}
}