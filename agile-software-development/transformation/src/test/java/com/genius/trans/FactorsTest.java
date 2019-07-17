package com.genius.trans;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FactorsTest {

	@Test
	public void factors() {
		Assertions.assertEquals(factorsOf(1), intList());
		Assertions.assertEquals(factorsOf(2), intList(2));
		Assertions.assertEquals(factorsOf(3), intList(3));
		Assertions.assertEquals(factorsOf(4), intList(2, 2));
		Assertions.assertEquals(factorsOf(5), intList(5));
		Assertions.assertEquals(factorsOf(6), intList(2, 3));
		Assertions.assertEquals(factorsOf(7), intList(7));
		Assertions.assertEquals(factorsOf(8), intList(2, 2, 2));
		Assertions.assertEquals(factorsOf(9), intList(3, 3));
	}

	private List<Integer> intList(Integer... i) {
		return Arrays.asList(i);
	}

	private List<Integer> factorsOf(int n) {
		Stream.of(1, 2).collect(Collectors.filtering(e -> e > 1, Collectors.toList()));
		List<Integer> factors = new ArrayList<>();
		for (int divisor = 2; n > 1; divisor++) {
			for (; n % divisor == 0; n /= divisor)
				factors.add(divisor);
		}
		if (n > 1)
			factors.add(n);
		return factors;


	}
}


