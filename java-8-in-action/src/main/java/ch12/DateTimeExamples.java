package ch12;

import java.text.MessageFormat;
import java.time.*;
import java.time.temporal.ChronoField;
import java.util.TimeZone;

public class DateTimeExamples {
	public static void main(String[] args) {
		getLocalDate();
	}

	private static void getLocalDate() {
		LocalDate date = LocalDate.of(2017, 4, 24);
		int year = date.getYear();
		Month month = date.getMonth();
		int day = date.getDayOfMonth();
		DayOfWeek dow = date.getDayOfWeek();
		int len = date.lengthOfMonth();
		Object[] objects = {date, year, month, day, dow, len};
		MessageFormat form = new MessageFormat("date : {0}, year : {1, number}, month : {2}, day : {3}, dow : {4}, len : {5}");
		System.out.println(form.format(objects));

		int y = date.get(ChronoField.YEAR);
		int m = date.get(ChronoField.MONTH_OF_YEAR);
		int d = date.get(ChronoField.DAY_OF_MONTH);

		System.out.printf("Year : %s, Month : %s, Day : %s\n", y, m, d);

		LocalTime time = LocalTime.of(13, 45, 20);
		int hour = time.getHour();
		int minute = time.getMinute();
		int second = time.getSecond();
		System.out.printf("Hour : %s, Minute : %s, Second : %s\n", hour, minute, second);

		LocalDate dating = LocalDate.parse("2017-04-24");
		System.out.printf("%s \n", dating.toString());

		LocalDateTime dt1 = LocalDateTime.of(2016, Month.APRIL, 18, 13, 45, 20);
		LocalDateTime dt2 = LocalDateTime.of(date, time);
		LocalDateTime dt3 = date.atTime(13, 45, 20);
		LocalDateTime dt4 = date.atTime(time);
		LocalDateTime dt5 = time.atDate(date);

		LocalDate date1 = dt1.toLocalDate();
		LocalTime time1 = dt1.toLocalTime();

		ZoneId zoneId = TimeZone.getDefault().toZoneId();
		System.out.println(zoneId);

		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println(localDateTime);

		Instant instant = Instant.now();
		instant.atZone(zoneId);
		System.out.println(instant);
	}
}