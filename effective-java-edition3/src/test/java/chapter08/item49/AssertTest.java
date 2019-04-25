package chapter08.item49;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class AssertTest {

    @Test
    public void fromToTest() {
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now().plusDays(1);
        Assert.fromTo(start, end);
    }
}