package ch01.Q1_01_Is_Unique;

import org.junit.Assert;
import org.junit.Test;

public class Tester {

	@Test
	public void test() {
		Assert.assertEquals(true, QuestionA.isUniqueChars("fal"));
		Assert.assertEquals(false, QuestionA.isUniqueChars("fall"));
	}
}
