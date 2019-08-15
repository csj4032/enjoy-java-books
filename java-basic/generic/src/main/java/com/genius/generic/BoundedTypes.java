package com.genius.generic;

public class BoundedTypes {

	public static <T> int countGreaterThanRaw(T[] anArray, T elem) {
		int count = 0;
		for (T e : anArray)
			//if (e > elem)
				++count;
		return count;
	}

	public static <T extends Comparable> int countGreaterThanNoType(T[] anArray, T elem) {
		int count = 0;
		for (T e : anArray)
			if (e.compareTo(elem) > 0)
				++count;
		return count;
	}

	public static <T extends Comparable<T>> int countGreaterThan(T[] anArray, T elem) {
		int count = 0;
		for (T e : anArray)
			if (e.compareTo(elem) > 0)
				++count;
		return count;
	}

	public static void main(String[] args) {
		countGreaterThanNoType(new Integer[]{1,2,3}, "A");
		countGreaterThan(new Integer[]{1,2,3}, 1);
	}
}
