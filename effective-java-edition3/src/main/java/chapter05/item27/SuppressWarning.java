package chapter05.item27;

import java.util.Arrays;

public class SuppressWarning {

	private Object[] elements;
	private int size;

	public <E> E[] toArray(E[] a) {
		size = elements.length;
		@SuppressWarnings(value = "unchecked")
		E[] result = (E[]) Arrays.copyOf(elements, size, Number[].class);
		return result;
	}

	public void setElements(Object[] elements) {
		this.elements = elements;
	}
}