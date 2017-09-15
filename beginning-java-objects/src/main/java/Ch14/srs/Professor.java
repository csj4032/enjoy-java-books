package ch14.srs;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Professor extends Person {

	private String title;
	private String department;
	private ArrayList<Section> teaches;

	public Professor(String name, String ssn, String title, String dept) {
		super(name, ssn);
		setTitle(title);
		setDepartment(dept);
		teaches = new ArrayList<Section>();
	}

	public void display() {
		super.display();
		System.out.println("Professor-Specific Information:");
		System.out.println("\tTitle:  " + getTitle());
		System.out.println("\tTeaches for Dept.:  " + getDepartment());
		displayTeachingAssignments();
		System.out.println();
	}

	public void displayTeachingAssignments() {

	}

	public void agreeToTeach(Section s) {
		teaches.add(s);
		s.setInstructor(this);
	}

	public String toString() {
		return getName() + " (" + getTitle() + ", " + getDepartment() + ")";
	}
}