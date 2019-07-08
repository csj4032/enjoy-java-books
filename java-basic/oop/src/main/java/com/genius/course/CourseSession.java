package com.genius.course;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CourseSession {

	private static AtomicInteger count = new AtomicInteger();
	private final String department;
	private final String number;
	private final LocalDate startDate;
	private int numberOfCredits;
	private List<Student> students = new ArrayList<>();

	public static CourseSession of(String department, String number, LocalDate startDate) {
		return new CourseSession(department, number, startDate);
	}

	public CourseSession(final String department, final String number) {
		this(department, number, LocalDate.now());
	}

	public CourseSession(final String department, final String number, final LocalDate startDate) {
		this.department = department;
		this.number = number;
		this.startDate = startDate;
	}

	public static void resetCount() {
		count.set(0);
	}

	public static int getCount() {
		return count.intValue();
	}

	public static void incrementCount() {
		count.incrementAndGet();
	}

	public String getDepartment() {
		return this.department;
	}

	public String getNumber() {
		return this.number;
	}

	public int getNumberOfStudents() {
		return students.size();
	}

	public void enroll(Student student) {
		student.addCredits(numberOfCredits);
		students.add(student);
	}

	public List<Student> getAllStudents() {
		return students;
	}

	public Student get(int index) {
		return students.get(index);
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return startDate.plusDays(16 * 7 - 3);
	}

	public void setNumberOfCredits(int numberOfCredits) {
		this.numberOfCredits = numberOfCredits;
	}
}
