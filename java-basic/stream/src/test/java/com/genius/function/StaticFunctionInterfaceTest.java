package com.genius.function;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.genius.function.StaticFunctionInterface.converter;
import static com.genius.function.StaticFunctionInterface.mapping;

@Slf4j
public class StaticFunctionInterfaceTest {

	private Function<Original, Destination> mapping = o -> Destination.builder().name(o.getFirstName() + o.getLastName()).email(o.getEmail()).build();

	@Test
	public void converting() {
		var originals = List.of(
				defaultOriginal(a -> b -> c -> new Original(a, b, c)),
				Original.builder().firstName("1").lastName("A").email("가").build(),
				Original.builder().firstName("2").lastName("B").email("나").build(),
				Original.builder().firstName("3").lastName("C").email("다").build(),
				Original.builder().firstName("4").lastName("D").email("다").build()
		);

		var destinations1 = originals.stream().map(converter()).collect(Collectors.toList());
		var destinations2 = originals.stream().map(mapping()).collect(Collectors.toList());
		var destinations3 = originals.stream().map(mapping).collect(Collectors.toList());

		Assertions.assertNotEquals(destinations1, destinations2);
		Assertions.assertNotEquals(destinations1, destinations3);
		Assertions.assertNotEquals(destinations2, destinations3);

		log.info(destinations1.toString());
		log.info(destinations2.toString());
		log.info(destinations3.toString());
	}

	public Original defaultOriginal(Function<String, Function<String, Function<String, Original>>> function) {
		return function.apply("0").apply("Z").apply("하");
	}
}