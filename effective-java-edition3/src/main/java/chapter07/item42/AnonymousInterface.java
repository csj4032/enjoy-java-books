package chapter07.item42;

public interface AnonymousInterface<T> extends AnonymousInterfaceSuper {

	default T goo() {
		return (T) "goo";
	}

	T foo();

}
