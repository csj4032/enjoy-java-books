package com.genius.calendar;

import java.time.LocalDate;

public class Calendar {

    private static final int[] normalMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final int[] leapMonth = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final LocalDate startDate = LocalDate.of(1, 1, 1);
    private final int year;
    private final int month;
    private final int dayOfWeek = 7;
    private final int dayOfYear = 365;

    public Calendar() {
        this(LocalDate.now().getYear(), LocalDate.now().getMonth().getValue());
    }

    public Calendar(int year, int month) {
        this.year = year;
        this.month = month;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDayOfMonthFirstDay() {
        return (getSumOfDays() + getSumOfDaysOnYear() + 1) % dayOfWeek;
    }

    public int getSumOfDays() {
        int normal = (year - startDate.getYear());
        int leap = (normal / 4) - ((normal / 100) - (normal / 400));
        return (normal * dayOfYear) + leap;
    }

    public int getSumOfDaysOnYear() {
        int sumOfDays = 0;
        int[] dayOfMonth = isLeap() ? leapMonth : normalMonth;
        for (int i = 0; i < month - 1; i++) {
            sumOfDays += dayOfMonth[i];
        }
        return sumOfDays;
    }

    public boolean isLeap() {
        if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
            return true;
        }
        return false;
    }

    public int currentMonthEndDay() {
        return isLeap() ? leapMonth[month-1] : normalMonth[month-1];
    }
}