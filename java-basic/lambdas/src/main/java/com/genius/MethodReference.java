package com.genius;

public class MethodReference {

	public static void main(String[] args) {
		//String::valueOf;
		//() -> String.valueOf("");
		convert(100, (number) -> String.valueOf(number));
		convert(100, String::valueOf);

		Conversion a = (x) -> String.valueOf(x);
		Conversion b = String::valueOf;
	}

	@FunctionalInterface
	interface Conversion {
		String convert(Integer number);
	}

	public static String convert(Integer number, Conversion function) {
		return function.convert(number);
	}
}
