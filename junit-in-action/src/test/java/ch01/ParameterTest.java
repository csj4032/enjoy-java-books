package ch01;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParameterTest {

	@ParameterizedTest(name = "{index} => expected={0}, valueOne={1}, valueTwo={2}")
	@CsvSource({"3, 1, 2", "8, 3, 5"})
	public void sum(double expected, double valueOne, double valueTwo) {
		Calculator cal = new Calculator();
		assertEquals(expected, cal.add(valueOne, valueTwo));
	}
}