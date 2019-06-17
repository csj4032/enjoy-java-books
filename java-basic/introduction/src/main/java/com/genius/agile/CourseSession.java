package com.genius.agile;

import com.genius.agile.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CourseSession {

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
}
