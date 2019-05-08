package chapter09.item60;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

public class NaturalOrderTest {

    private Comparator<Integer> naturalOrder = (i, j) -> (i < j) ? -1 : (i == j ? 0 : 1);

    @Test
    public void naturalOrder() {
        Assertions.assertNotEquals(0, naturalOrder.compare(new Integer(42), new Integer(42)));
        Assertions.assertEquals(0, naturalOrder.compare(42, 42));
    }
}