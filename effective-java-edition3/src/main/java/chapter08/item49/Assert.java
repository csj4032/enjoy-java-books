package chapter08.item49;

import java.time.LocalDate;

public class Assert {

    public static void fromTo(LocalDate from, LocalDate to) {
        assert from != null;
        assert to != null;
        assert from.isBefore(to);
    }
}
