package com.genius.collections;

import java.sql.DriverManager;
import java.util.Iterator;
import java.util.List;

public class IterableInterface {

	public void iterable() {
		Iterator<Integer> methodReference = ((Iterable) List.of(1, 2, 3, 4, 5)::iterator).iterator();
		Iterator<Integer> lambda = ((Iterable) (() -> List.of(1, 2, 3, 4, 5).iterator())).iterator();
		Iterator<Integer> anonymouse = new Iterable<Integer>() {
			@Override
			public Iterator<Integer> iterator() {
				return List.of(1, 2, 3, 4, 5).iterator();
			}
		}.iterator();
	}
}