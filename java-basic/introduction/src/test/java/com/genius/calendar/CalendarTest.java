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
	@DisplayName("현재 년 현재 달까지 총 일수")
	public void getDayOfYear() {
		Calendar calendar = new Calendar(2017, 5);
		Assertions.assertEquals(120, calendar.getDayOfYear());
	}

	@Test
	@Order(4)
	@DisplayName("현재 월 첫번째 요일")
	public void dayOfMonthFirstDayTest() {
		Calendar calendar = new Calendar(2017, 5);
		Assertions.assertEquals(1, calendar.getDayOfMonth());
	}

	@Test
	@Order(5)
	@DisplayName("현재 년도 까지 총일 수")
	public void getSumOfDays() {
		Calendar calendar = new Calendar(2017, 5);
		Assertions.assertEquals(736329, calendar.getDayOfYears());
	}

	@Test
	@Order(6)
	@DisplayName("윤년 확인")
	public void leapTest() {
		Calendar calendar = new Calendar(400, 1);
		Assertions.assertTrue(calendar.isLeapYear());
	}

	@Test
	@Order(7)
	@DisplayName("출력 확인")
	public void printTest() {
		Calendar calendar = new Calendar(2017, 5);
		Printer printer = new Printer(calendar);
        printer.print();
	}
}