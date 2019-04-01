package ch01.Q1_01_Is_Unique;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tester {

	@Test
	public void test() {
		assertEquals(true, QuestionA.isUniqueChars("fal"));
		assertEquals(false, QuestionA.isUniqueChars("fall"));
	}
}
