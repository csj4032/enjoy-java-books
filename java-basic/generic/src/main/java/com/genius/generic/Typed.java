package com.genius.generic;

import java.util.ArrayList;
import java.util.List;

public class Typed {

	public void type() {
		List<String> strings = new ArrayList();
		strings.add("this");
		strings.add("is");
		strings.add("a");
		strings.add("RAW");
		strings.add("list");
		strings.add("of");
		strings.add("strings");

		for (Object o : strings) {
			String s = (String) o;
			System.out.printf("%7s has %d characters%n", s, s.length());
		}
	}
}
