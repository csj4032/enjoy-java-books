package chapter04.item20;

public class MixInClass implements Comparable, Cloneable, AutoCloseable {

	@Override
	public void close() throws Exception {

	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}
}
