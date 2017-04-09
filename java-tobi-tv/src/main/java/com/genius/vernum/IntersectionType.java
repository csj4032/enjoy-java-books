package com.genius.vernum;

import java.util.function.Consumer;
import java.util.function.Function;

public class IntersectionType {

	interface Hello extends Function {
		default void hello() {
			System.out.println("Hello");
		}
	}

	interface Hi extends Function {
		default void hi() {
			System.out.println("Hi");
		}
	}

	interface Print {
		default void print(String str) {
			System.out.println(str);
		}
	}

	public static void main(String[] args) {
		run((Function & Hello & Hi & Print) s -> s, o -> {
			o.hello();
			o.hi();
			o.print("Lambda");
		});
	}

	private static <T extends Function> void run(T t, Consumer<T> c) {
		c.accept(t);
	}

	private static <T extends Function & Hello & Hi> void hello(T t) {
		t.hello();
		t.hi();
	}
}