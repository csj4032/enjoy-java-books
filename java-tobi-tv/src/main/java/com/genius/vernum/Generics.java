package com.genius.vernum;

import java.util.*;

// 컴파일 시점에 타입 체크
public class Generics {

	static class A {
	}

	static class B extends A {
	}

	static <T extends Comparable<T>> long countGreaterThan(T[] arr, T elem) {
		return Arrays.stream(arr).filter(s -> s.compareTo(elem) > 0).count();
	}

	static <T> void method1(List<T> list) {

	}

	static void method2(List<?> list) {

	}

	static void method3(List<Object> list) {

	}

	private static boolean isEmpty(List list) {
		return list.size() == 0;
	}

	private static long frequency(Collection collection, Object elem) {
		return collection.stream().filter(s -> s.equals(elem)).count();
	}

	public static void main(String[] args) {

		List<Integer> list = Arrays.asList(1, 2, 4, 5);
		method1(list);
		method2(list);
		//method3(list);

		System.out.println(isEmpty(list));

		frequency(list, 3);
		Collections.frequency(list, 3);

		//Integer[] arr = new Integer[]{1, 2, 3, 4, 5};
		String[] arr = new String[]{"a", "b", "c", "d", "e"};
		System.out.println(countGreaterThan(arr, "a"));

		Integer i = 10;
		Number n = i;

		Integer[] a = new Integer[]{1, 2, 3};
		Number[] b = a;

		//List<?> list; // 리스트에 비조작 메소드만 사용
		//List<Object> list1; //
		//List<? extends Object> list2; //

		//printList(Arrays.asList(1,2,3));
		//printList2(Arrays.asList(1,2,3));

		//List<Integer> list3 = Arrays.asList(1, 2, 3);
		//printList2(list3);

		List<B> listB = new ArrayList<>();
		List<? extends A> l1 = listB;
		List<? super B> l2 = listB;
		//List<? super A> l3 = listB;

		// 와일카드의 사용
		// l1.add(new B());
		l1.add(null);
		listB.add(new B());

		List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5);
		reverse(list);
	}

	static void reverse(List<?> list) {
		reverseHelper(list);
	}

	private static <T> void reverseHelper(List<T> list) {
		List<T> temp = new ArrayList(list);
		for (int i = 0; i < list.size(); i++) {
			list.set(i, temp.get(list.size() - i - 1));
		}
	}
}