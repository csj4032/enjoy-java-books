package chapter09.item63;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

public class StatementTest {

    @Test
    public void statementTest() {
        Assertions.assertTimeout(Duration.ofMillis(12000), () -> {
            String result = "";
            for (int i = 0; i < 100000; i++)
                result += "result " + i;
        });

        Assertions.assertTimeout(Duration.ofMillis(100), () -> {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < 100000; i++)
                result.append("result_" + i);
        });
    }
}
