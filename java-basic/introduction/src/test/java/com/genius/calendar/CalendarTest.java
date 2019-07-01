package com.genius.calendar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.DayOfWeek;
import java.time.Month;

@DisplayName("달력 출력 하기")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CalendarTest {

    @Test
    @Order(1)
    @DisplayName("달력 시작 년월일 확인")
    public void calendarStartDateTest() {
        Calendar calendar = new Calendar();
        Assertions.assertEquals(1, calendar.getStartDate().getYear());
        Assertions.assertEquals(Month.JANUARY, calendar.getStartDate().getMonth());
        Assertions.assertEquals(1, calendar.getStartDate().getDayOfMonth());
        Assertions.assertEquals(DayOfWeek.MONDAY, calendar.getStartDate().getDayOfWeek());
    }

    @Test
    @Order(2)
    @DisplayName("달력 현재 년월 확인")
    public void calendarCurrentYearMonth() {
        Calendar calendar = new Calendar(2017, 5);
        Assertions.assertEquals(2017, calendar.getYear());
        Assertions.assertEquals(5, calendar.getMonth());
    }

    @Test
    @Order(3)
    @DisplayName("현재 년 현재달까지 총 일수")
    public void getSumOfDaysOnYear() {
        Calendar calendar = new Calendar(2017, 5);
        Assertions.assertEquals(120, calendar.getSumOfDaysOnYear());
    }

    @Test
    @Order(4)
    @DisplayName("현재 월 첫번째 요일")
    public void dayOfMonthFirstDayTest() {
        Calendar calendar = new Calendar(2017, 5);
        Assertions.assertEquals(1, calendar.getDayOfMonthFirstDay());
    }

    @Test
    @Order(5)
    @DisplayName("현재 월 첫번째 요일")
    public void getSumOfDays() {
        Calendar calendar = new Calendar(2017, 5);
        Assertions.assertEquals(736329, calendar.getSumOfDays());
    }

    @Test
    @Order(6)
    @DisplayName("윤년 확인")
    public void leapTest() {
        Calendar calendar = new Calendar(400, 1);
        Assertions.assertTrue(calendar.isLeap());
    }

    @Test
    @Order(7)
    @DisplayName("출력 확인")
    public void printTest() {
        Calendar calendar = new Calendar(2017, 5);
        Print print =new Print(calendar);
        print.print();
    }
}