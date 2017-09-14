package chapter02.item06;

import java.util.Arrays;

public class Stack {

	private Object[] elements;
	private int size = 0;
	private static final int DEFAULT_INITIAL_CAPACITY = 16;

	public Stack() {
		elements = new Object[DEFAULT_INITIAL_CAPACITY];
	}

	public void push(Object e) {
		ensureCapacity();
		elements[size++] = e;
	}

//	public Object pop() {
//		if (size == 0) {
//			throw new EmptyStackException();
//		}
//		return elements[--size];
//	}

	public Object pop() {
		if (size == 0) {
			throw new EmptyStackException();
		}
		Object result = elements[--size];
		elements[size] = null; // 만기 참조 제거
		return result;
	}

	private void ensureCapacity() {
		if (elements.length == size) elements = Arrays.copyOf(elements, 2 * size + 1);
	}
}
