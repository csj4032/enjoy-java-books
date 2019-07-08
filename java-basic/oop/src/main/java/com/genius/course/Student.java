package com.genius.course;

public class Student {

	enum Grade {A, B, C, D, F}

	public static final int CREDITS_REQUIRED_FOR_FULL_TIME = 12;

	private final String name;
	private int credits;

	public Student(String name, int credits) {
		this.name = name;
		this.credits = credits;
	}

	public Student(String name) {
		this(name, 0);
	}

	public String getName() {
		return this.name;
	}

	public int getCredits() {
		return credits;
	}

	public void addCredits(int credits) {
		this.credits += credits;
	}

	public boolean isFullTime() {
		return credits >= CREDITS_REQUIRED_FOR_FULL_TIME;
	}
}