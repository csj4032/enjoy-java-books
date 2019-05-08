package chapter09.item59;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomTest {

    @Test
    public void nextInt() {
        Random random = new Random();
        random.nextInt(100);
        ThreadLocalRandom.current().nextInt(100);
    }

    @Test void transferTo() throws IOException {
        try (InputStream in = new URL("http://www.google.co.kr").openStream()){
            in.transferTo(System.out);
        }
    }
}