package chapter08.item50;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class PeriodTest {

    @Test
    public void periodTest() {
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now().plusDays(1);
        Period period = new Period(start, end);
        end.plusDays(1);
        Assertions.assertEquals(end.getDayOfMonth(), period.getEnd().getDayOfMonth());
    }
}
