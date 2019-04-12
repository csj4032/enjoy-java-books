package ch01;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParameterizedTests {

	private double expected;
	private double valueOne;
	private double valueTwo;

	@ParameterizedTest
	public static Collection<Integer[]> getTestParameters() {
		return Arrays.asList(new Integer[][]{
				{2, 1, 1},
				{3, 2, 1},
				{4, 3, 1}
		});
	}

	public ParameterizedTests(double expected, double valueOne, double valueTwo) {
		this.expected = expected;
		this.valueOne = valueOne;
		this.valueTwo = valueTwo;
	}

	@Test
	public void sum() {
		Calculator cal = new Calculator();
		assertEquals(expected, cal.add(valueOne, valueTwo), 0);
	}
}
