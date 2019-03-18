package chapter04.item21;

public interface DefaultMethod {

	default void greeting() {
		System.out.println("DefaultMethod greeting");
	}

	void hello();
}
