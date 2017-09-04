package ch01;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

	@Test
	public void add() {
		Calculator calculator = new Calculator();
		double result = calculator.add(10, 50);
		assertEquals(60, result, 0);
	}
}