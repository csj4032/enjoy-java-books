package com.genius.duality;

public class Student implements Comparable<Student> {
	private Integer grade;
	private Integer total;

	@Override
	public int compareTo(Student other) {
		int i = this.grade.compareTo(other.grade);
		if (i != 0) return i;
		return this.total.compareTo(other.total);
	}
}
