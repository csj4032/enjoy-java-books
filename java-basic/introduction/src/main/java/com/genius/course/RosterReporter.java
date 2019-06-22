package com.genius.course;

import static com.genius.course.ReportConstant.NEWLINE;

public class RosterReporter {

	private final CourseSession courseSession;
	public static final String ROSTER_REPORT_HEADER = "Student" + NEWLINE + "-" + NEWLINE;
	public static final String ROSTER_REPORT_FOOT = NEWLINE + "# students = ";
	private StringBuilder stringBuilder;

	public RosterReporter(CourseSession courseSession) {
		this.courseSession = courseSession;
	}

	public String getReport() {
		stringBuilder = new StringBuilder();
		writerHeaderAndBodyAndFoot();
		return stringBuilder.toString();
	}

	private void writerHeaderAndBodyAndFoot() {
		writerHeader();
		writerBody();
		writerFoot();
	}

	private void writerFoot() {
		stringBuilder.append(ROSTER_REPORT_FOOT + courseSession.getAllStudents().size() + NEWLINE);
	}

	private void writerBody() {
		for (Student student : courseSession.getAllStudents()) {
			stringBuilder.append(student.getName());
			stringBuilder.append(NEWLINE);
		}
	}

	private void writerHeader() {
		stringBuilder.append(ROSTER_REPORT_HEADER);
	}
}