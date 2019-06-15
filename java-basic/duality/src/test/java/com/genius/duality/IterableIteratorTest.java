package com.genius.duality;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

public class IterableIteratorTest {

	@Test
	public void iteratorTest() {
		Iterable<Integer> iterable = () -> new Iterator<>() {
			int i = 0;
			final static int max = 10;
			public boolean hasNext() { return i < max; }
			public Integer next() { return ++i; }
		};

		for (Iterator it = iterable.iterator(); it.hasNext(); ) {
			System.out.println(it.next());
		}

		for (Integer i : iterable ) {
			System.out.println(i);
		}
	}
}
