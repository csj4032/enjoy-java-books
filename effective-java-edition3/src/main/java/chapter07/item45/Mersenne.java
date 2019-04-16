package chapter07.item45;

import java.math.BigInteger;
import java.util.stream.Stream;

import static java.math.BigInteger.TWO;

public class Mersenne {

    static Stream<BigInteger> primes() {
        return Stream.iterate(TWO, BigInteger::nextProbablePrime);
    }
}
