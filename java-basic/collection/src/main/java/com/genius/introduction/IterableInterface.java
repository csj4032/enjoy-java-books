package com.genius.introduction;

import java.util.Iterator;
import java.util.List;

public class IterableInterface {

	public void iterable() {
		Iterator<Integer> iterator = List.of(1, 2, 3, 4, 5).iterator();
		Iterator<Integer> methodReference = ((Iterable) List.of(1, 2, 3, 4, 5)::iterator).iterator();
		Iterable<Integer> iterable = (List.of(1, 2, 3, 4, 5)::iterator);
		Iterator<Integer> lambda = ((Iterable) (() -> List.of(1, 2, 3, 4, 5).iterator())).iterator();
		Iterator<Integer> anonymouse = new Iterable<Integer>() {
			@Override
			public Iterator<Integer> iterator() {
				return List.of(1, 2, 3, 4, 5).iterator();
			}
		}.iterator();
	}
}