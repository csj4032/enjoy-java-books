package com.genius.streams;

import java.util.stream.Stream;

/**
 * https://dzone.com/articles/are-java-8-streams-truly-lazy-not-completely?edition=306229&utm_source=Daily%20Digest&utm_medium=email&utm_campaign=dd%202017-07-05
 */
public class LazyStream {

	public static void main(String[] args) {
		Stream.iterate(0, i -> i + 1).map(i -> i + 1).peek(i -> System.out.println("Map : " + i)).limit(5).forEach(i -> {});

		System.out.println();
		System.out.println();

		Stream.iterate(0, i -> i + 1).limit(5).map(i -> i + 1).peek(i -> System.out.println("Map : " + i)).forEach(i -> {});

		System.out.println();
		System.out.println();

		Stream.iterate(0, i -> i + 1).flatMap(i -> Stream.of(i, i, i, i)).map(i -> i + 1).peek(i -> System.out.println("Map: " + i)).limit(5).forEach(i -> {});

		System.out.println();
		System.out.println();

		Stream.iterate(0, i -> i + 1).flatMap(i -> Stream.of(i, i, i, i)).limit(5).map(i -> i + 1).peek(i -> System.out.println("Map: " + i)).forEach(i -> {});
	}
}