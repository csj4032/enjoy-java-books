package com.genius.srs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Vector;

@Getter
@Setter
@ToString
public class Professor extends Person {

	private String title;
	private String department;
	private Vector teaches;

	public Professor(String name, String ssn, String title, String department) {
		super(name, ssn);
		setTitle(title);
		setDepartment(department);
	}

	public void display() {
		super.display();
		System.out.println("Professor-Specific Information:");
		System.out.println("\tTitle:  " + getTitle());
		System.out.println("\tTeaches for Dept.:  " + getDepartment());
		displayTeachingAssignments();
	}

	private void displayTeachingAssignments() {
		System.out.println("Teaching Assignments for " + getName() + ":");
		if (teaches.size() == 0)
			System.out.println("\t(none)");
		else for (int i = 0; i < teaches.size(); i++) {

			Section s = (Section) teaches.elementAt(i);

			System.out.println("\tCourse No.:  " + s.getRepresentedCourse().getCourseNo());
			System.out.println("\tSection No.:  " + s.getSectionNo());
			System.out.println("\tCourse Name:  " + s.getRepresentedCourse().getCourseName());
			System.out.println("\tDay and Time:  " + s.getDayOfWeek() + " - " + s.getTimeOfDay());
			System.out.println("\t-----");
		}
	}

	public String toString() {
		return getName() + " (" + getTitle() + ", " + getDepartment() + ")";
	}
}
