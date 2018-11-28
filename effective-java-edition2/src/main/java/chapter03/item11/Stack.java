package chapter03.item11;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class Stack implements Cloneable {

	private Object[] elements;
	private int size = 0;
	private static final int DEFAULT_INITIAL_CAPACITY = 16;

	public Stack() {
		this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
	}

	public void push(Object e) {
		ensureCapacity();
		elements[size++] = e;
	}

	public Object pop() {
		if (size == 0) throw new EmptyStackException();
		Object result = elements[--size];
		elements[size] = null;
		return result;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Stack clone() {
		try {
			Stack result = (Stack) super.clone();
			// 릴리스 1.5부터는 배열에 Clone을 호출하면 반환되는 배열의 컴파일 시점 (compile-time) 자료형은 복제 대상 배열의 자료형과 같다.
			result.elements = elements.clone();
			return result;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}

	private void ensureCapacity() {
		if (elements.length == size)
			elements = Arrays.copyOf(elements, 2 * size + 1);
	}

	public static void main(String[] args) {
		Stack stack = new Stack();
		for (String arg : args)
			stack.push(arg);
		Stack copy = stack.clone();
		while (!stack.isEmpty())
			log.info(stack.pop() + " ");
		log.info("\n");
		while (!copy.isEmpty())
			log.info(copy.pop() + " ");
	}
}
