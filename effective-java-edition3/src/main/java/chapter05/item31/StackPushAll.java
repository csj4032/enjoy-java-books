package chapter05.item31;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

public class StackPushAll<E> extends Stack<E> {

	public void pushAll(Iterable<? extends E> src) {
		for (E e : src) {
			push(e);
		}
	}

	public void popAll(Collection<? super E> dst) {
		while (!isEmpty())
			dst.add(pop());
	}

	public static <E extends Comparable<? super E>> E max(List<? extends E> list) {
		return list.get(0);
	}

	public static void reverse(List<?> list) {
		rev(list);
	}

	private static <T> void rev(List<T> list) {
		List<T> tmp = new ArrayList<T>(list);
		for (int i = 0; i < list.size(); i++) {
			list.set(i, tmp.get(list.size() - i - 1));
		}
	}
}