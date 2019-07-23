package com.genius.calendar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static com.genius.calendar.Year.isLeap;

@DisplayName("달력 출력 하기")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CalendarTest {

	@Test
	@Order(1)
	@DisplayName("달력 시작 년월일 확인")
	public void calendarStartDateTest() {
		Calendar calendar = new Calendar();
		Assertions.assertNotNull(calendar);
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
	public void firstDayOfYear() {
		Month month = new Month(2017, 5);
		Assertions.assertEquals(120, month.firstDayOfYear(isLeap(2017)));
	}

	@Test
	@Order(4)
	@DisplayName("현재 년도 까지 총일 수")
	public void getDaysOfYears() {
		Assertions.assertEquals(736329, Year.getDaysOfYears(2017));
	}

	@Test
	@Order(5)
	@DisplayName("현재 월 첫번째 요일")
	public void dayOfMonthFirstDayTest() {
		Calendar calendar = new Calendar(2017, 5);
		Assertions.assertEquals(1, calendar.getDayOfMonth());
	}

	@Test
	@Order(6)
	@DisplayName("윤년 확인")
	public void leapTest() {
		Year year = new Year(400);
		Assertions.assertTrue(isLeap(year.getValue()));
	}

	@Test
	@Order(7)
	@DisplayName("출력 확인")
	public void printTest() {
		new PrinterEnglish(new Calendar(2017, 5)).print();
	}

	@Test
	@Order(8)
	@DisplayName("출력 확인")
	public void printTestKorean() {
		new PrinterKorean(new Calendar(2017, 5)).print();
	}
}