package com.genius.collections;

import java.util.Iterator;

public class CollectionInterface {

	public void collection() {
		java.util.Collection collection = new java.util.Collection() {
			@Override
			public int size() {
				return 0;
			}

			@Override
			public boolean isEmpty() {
				return false;
			}

			@Override
			public boolean contains(Object o) {
				return false;
			}

			@Override
			public Iterator iterator() {
				return null;
			}

			@Override
			public Object[] toArray() {
				return new Object[0];
			}

			@Override
			public Object[] toArray(Object[] a) {
				return new Object[0];
			}

			@Override
			public boolean add(Object o) {
				return false;
			}

			@Override
			public boolean remove(Object o) {
				return false;
			}

			@Override
			public boolean containsAll(java.util.Collection c) {
				return false;
			}

			@Override
			public boolean addAll(java.util.Collection c) {
				return false;
			}

			@Override
			public boolean removeAll(java.util.Collection c) {
				return false;
			}

			@Override
			public boolean retainAll(java.util.Collection c) {
				return false;
			}

			@Override
			public void clear() {

			}
		};
	}
}
