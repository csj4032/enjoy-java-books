package com.genius.collections;

import java.util.Iterator;
import java.util.List;

public class IterableInterface {

	public void iterable() {
		List<Integer> list = List.of(1, 2, 3, 4, 5);
		Iterator<Integer> iterator = ((Iterable) list::iterator).iterator();
		for (; iterator.hasNext(); ) {
			System.out.println(iterator.next());
		}
	}
}