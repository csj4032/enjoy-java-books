package chapter04.item15;

import java.math.BigInteger;

public class ImmutableClass {

	public static void main(String[] args) {
		BigInteger bigInteger = BigInteger.ONE;
		BigInteger bigInteger1 = bigInteger.negate();
		bigInteger.modPow(BigInteger.ONE, BigInteger.TEN);
	}
}
