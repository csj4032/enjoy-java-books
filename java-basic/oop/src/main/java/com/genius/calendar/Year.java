package com.genius.calendar;

import java.util.stream.IntStream;

public class Year {

    private final int value;

    public Year(int year) {
        this.value = year;
    }

    public int getValue() {
        return value;
    }

    public static int getDaysOfYears(int value) {
        return IntStream.range(1, value).map(i -> isLeap(i) ? 366 : 365).sum();
    }

    public static boolean isLeap(int value) {
        return ((value & 3) == 0) && ((value % 100 != 0 || value % 400 == 0));
    }
}
