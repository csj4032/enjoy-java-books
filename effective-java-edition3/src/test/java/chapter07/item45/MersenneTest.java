package chapter07.item45;

import org.junit.jupiter.api.Test;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.TWO;

public class MersenneTest {

    @Test
    public void mersenne() throws FileNotFoundException {
        Mersenne.primes().map(p -> TWO.pow(p.intValueExact()).subtract(ONE))
                .filter(mersenne -> mersenne.isProbablePrime(50))
                .limit(5)
                .forEach(System.out::println);

        Mersenne.primes().map(p -> TWO.pow(p.intValueExact()).subtract(ONE))
                .filter(mersenne -> mersenne.isProbablePrime(50))
                .limit(5)
                .forEach(mp -> System.out.println(mp.bitLength() + " : " + mp));


        DataInputStream dataInputStream = new DataInputStream(null);
        InputStreamReader inputStreamReader = new InputStreamReader(dataInputStream);


        byte[] bytes = new byte[]{49 ,50 ,51 ,52};
        ByteBuffer buff = ByteBuffer.allocate(bytes.length);
        buff.clear();
        buff.order(ByteOrder.BIG_ENDIAN);
        buff.put(bytes);
        buff.flip();
        System.out.println(buff.getInt());
    }
}
