package com.genius.generic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RawTypes {

	public void rawType() {
		List strings = new ArrayList();
		strings.add("this");
		strings.add("is");
		strings.add("a");
		strings.add("RAW");
		strings.add("list");
		strings.add("of");
		strings.add("strings");
		strings.add(LocalDate.now());

		for (Object o : strings) {
			String s = (String) o;
			System.out.printf("%7s has %d characters%n", s, s.length());
		}
	}
}