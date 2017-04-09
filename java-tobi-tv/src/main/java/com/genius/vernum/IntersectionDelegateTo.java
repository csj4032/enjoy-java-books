package com.genius.vernum;

import java.util.function.Consumer;

public class IntersectionDelegateTo {

	interface DelegateTo<T> {
		T delegate();
	}

	interface Hello extends DelegateTo<String> {
		default void hello() {
			System.out.printf("Hello " + delegate());
		}
	}

	interface UpperCase extends DelegateTo<String> {
		default void upperCase() {
			System.out.printf(delegate().toUpperCase());
		}
	}

	public static void main(String[] args) {
		run((DelegateTo<String> & Hello & UpperCase) () -> "Genius Choi", o -> {
			o.hello();
			o.upperCase();
		});
	}

	private static <T extends DelegateTo<S>, S> void run(T t, Consumer<T> consumer) {
		consumer.accept(t);
	}
}