package com.genius;

public class PrimeGenerator {

	private static boolean[] isCrossed;
	private static int[] result;

	public static int[] generatePrimes(int maxValue) {
		if (maxValue < 2) {
			return new int[0];
		} else {
			initializeArrayOfIntegers(maxValue);
			crossOutMultiples();
			putUncrossedIntegerIntoResult();
			return result;
		}
	}

	private static void initializeArrayOfIntegers(int maxValue) {
		isCrossed = new boolean[maxValue + 1];
		for (int i = 2; i < isCrossed.length; i++)
			isCrossed[i] = false;
	}

	private static void crossOutMultiples() {
		for (int i = 2; i <= calcMaxPrimeFactor(); i++) {
			if (notCrossed(i)) {
				crossOutMultiplesOf(i);
			}
		}
	}

	private static void crossOutMultiplesOf(int i) {
		for (int j = 2 * i; j < isCrossed.length; j += i) {
			isCrossed[j] = true;
		}
	}

	private static void putUncrossedIntegerIntoResult() {
		result = new int[numberOfUncrossedIntegers()];
		for (int i = 2, j = 0; i < isCrossed.length; i++) {
			if (notCrossed(i))
				result[j++] = i;
		}
	}

	private static int numberOfUncrossedIntegers() {
		int count = 0;
		for (int i = 2; i < isCrossed.length; i++) {
			if (notCrossed(i))
				count++;
		}
		return count;
	}

	private static boolean notCrossed(int i) {
		return isCrossed[i] == false;
	}

	private static int calcMaxPrimeFactor() {
		return (int) Math.sqrt(isCrossed.length) + 1;
	}
}