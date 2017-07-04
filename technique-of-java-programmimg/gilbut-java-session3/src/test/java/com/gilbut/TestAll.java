package com.gilbut;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestAll extends TestCase {

	public static Test suite() {
		TestSuite suite = new TestSuite("All test");
		suite.addTest(new TestSuite(TestDateUtil.class));
		suite.addTestSuite(TestFileUtil.class);
		suite.addTestSuite(TestAssert.class);
		return suite;
	}
}