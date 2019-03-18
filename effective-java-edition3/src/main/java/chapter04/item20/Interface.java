package chapter04.item20;

public interface Interface extends AutoCloseable {

	default String defaultMethod() {
		return "defaultMethod";
	}

	void interfaceMethod();
}
