package com.genius;

import java.util.concurrent.Callable;
import java.util.function.Function;

public class ParticularArbitrary {

	public static void main(String[] args) {

		// Particular
		// x::toString
		// () -> x.toString()
		Callable<String> x = () -> "Hello";
		ConstructorReference.Factory<String> s = x::toString;

		// Arbitrary
		// String::toString
		// (y) -> y.toString()
		Function<String, String> y = String::toString;

	}
}