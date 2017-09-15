package chapter05.item28;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.*;

public class RecursiveTypeBound {

	public static <T extends Comparable<? super T>> T max(List<? extends T> list) {
		Iterator<? extends T> i = list.iterator();
		T result = i.next();
		while (i.hasNext()) {
			T t = i.next();
			if (t.compareTo(result) > 0) result = t;
		}
		return result;
	}

	public static void main(String[] args) {
		List<String> list = Arrays.asList("A", "B", "C");
		List<Integer> list2 = Arrays.asList(1, 2, 3, 4);
		System.out.println(max(list));
		System.out.println(max(list2));
	}
}