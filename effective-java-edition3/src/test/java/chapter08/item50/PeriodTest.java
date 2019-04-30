package chapter08.item50;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PeriodTest {

    @Test
    public void periodTest() {
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now().plusDays(1);
        Period period = new Period(start, end);
        end.plusDays(1);
        Assertions.assertEquals(end.getDayOfMonth(), period.getEnd().getDayOfMonth());

        Date date1 = new DateSub();
        Date date2 = new DateSub();
        Date startDate = (Date) date1.clone();
        Date endDate = (Date) date2.clone();

        DateSub.getDates().get(0).setTime(0);
        DateSub.getDates().get(1).setTime(10);

        Assertions.assertEquals(0, startDate.getTime());
        Assertions.assertEquals(10, endDate.getTime());
    }
}

class DateSub extends Date {
    private static List<Date> dates = new ArrayList<>();

    public String name() {
        return super.toString();
    }

    @Override
    public Date clone() {
        dates.add(this);
        return this;
    }

    public static List<Date> getDates() {
        return dates;
    }
}
