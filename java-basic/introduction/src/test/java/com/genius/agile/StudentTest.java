package com.genius.agile;

import com.genius.intro.course.Student;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StudentTest {

	@Test
	public void testCreat() {
		Student student = new Student("genius");
		String studentName = student.getName();
		Assertions.assertNotNull(student);
		Assertions.assertEquals("genius", studentName);
	}
}