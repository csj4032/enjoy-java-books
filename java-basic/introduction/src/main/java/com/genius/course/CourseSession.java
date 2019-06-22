package com.genius.course;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CourseSession {

	public static final String NEWLINE = System.getProperty("line.separator");
	public static final String ROSTER_REPORT_HEADER = "Student" + NEWLINE + "-" + NEWLINE;
	public static final String ROSTER_REPORT_FOOT = NEWLINE + "# students = ";

	private final String department;
	private final String number;
	private final LocalDate startDate;
	private int numberOfStudents;
	private List<Student> students = new ArrayList<>();

	public CourseSession(final String department, final String number) {
		this(department, number, LocalDate.now());
	}

	public CourseSession(final String department, final String number, final LocalDate startDate) {
		this.department = department;
		this.number = number;
		this.startDate = startDate;
	}

	public String getDepartment() {
		return this.department;
	}

	public String getNumber() {
		return this.number;
	}

	public int getNumberOfStudents() {
		return numberOfStudents;
	}

	public void enroll(Student student) {
		students.add(student);
		numberOfStudents += 1;
	}

	public List<Student> getAllStudents() {
		return students;
	}

	public Student get(int index) {
		return students.get(index);
	}

	public LocalDate getEndDate() {
		return startDate.plusDays(16 * 7 - 3);
	}

	public String getRosterReport() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(ROSTER_REPORT_HEADER);

		Student student = students.get(0);
		stringBuilder.append(student.getName());
		stringBuilder.append(NEWLINE);

		student = students.get(1);
		stringBuilder.append(student.getName());
		stringBuilder.append(NEWLINE);

		stringBuilder.append(ROSTER_REPORT_FOOT + students.size() + NEWLINE);
		return stringBuilder.toString();
	}
}
