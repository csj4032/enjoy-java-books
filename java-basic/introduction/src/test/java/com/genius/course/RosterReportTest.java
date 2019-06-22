package com.genius.course;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.genius.course.RosterReporter.*;

public class RosterReportTest {

	@Test
	public void testRosterReport() {
		CourseSession session = new CourseSession("ENGL", "101", LocalDate.of(2019, 3, 2));
		session.enroll(new Student("A"));
		session.enroll(new Student("B"));

		RosterReporter rosterReport = new RosterReporter(session);
		Assertions.assertEquals(ROSTER_REPORT_HEADER + "A" + ReportConstant.NEWLINE + "B" + ReportConstant.NEWLINE + ROSTER_REPORT_FOOT + "2" + ReportConstant.NEWLINE, rosterReport.getReport());
	}
}