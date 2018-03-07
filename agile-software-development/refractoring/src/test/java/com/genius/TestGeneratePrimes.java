package com.genius;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestGeneratePrimes {

	@Test
	public void testPrimes() {
		int[] nullArray = PrimeGenerator.generatePrimes(0);
		assertEquals(nullArray.length, 0);

		int[] minArray = PrimeGenerator.generatePrimes(2);
		assertEquals(minArray.length, 1);
		assertEquals(minArray[0], 2);

		int[] threeArray = PrimeGenerator.generatePrimes(3);
		assertEquals(threeArray.length, 2);
		assertEquals(threeArray[0], 2);
		assertEquals(threeArray[1], 3);

		int[] centArray = PrimeGenerator.generatePrimes(100);
		assertEquals(centArray.length, 25);
	}

	@Test
	public void testExhaustive() {
		for (int i = 0; i < 500; i++) {
			verifyPrimeList(PrimeGenerator.generatePrimes(i));
		}
	}

	private void verifyPrimeList(int[] list) {
		for (int i : list) {
			verifyPrime(i);
		}
	}

	private void verifyPrime(int n) {
		for (int j = 2; j < n; j++) {
			assertTrue(n % j != 0);
		}
	}
}