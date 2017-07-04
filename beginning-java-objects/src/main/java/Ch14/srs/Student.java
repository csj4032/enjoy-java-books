package ch14.srs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Student extends Person {

	private String major;
	private String degree;
	private Transcript transcript;
	private ArrayList<Section> attends;

	public Student(String name, String ssn, String major, String degree) {
		super(name, ssn);
		this.setMajor(major);
		this.setDegree(degree);

		this.setTranscript(new Transcript(this));
		attends = new ArrayList<>();
	}

	public void display() {
		super.display();
		System.out.println("Student-Specific Information:");
		System.out.println("\tMajor:  " + this.getMajor());
		System.out.println("\tDegree:  " + this.getDegree());
		this.displayCourseSchedule();
		this.printTranscript();
		System.out.println();
	}

	public boolean isCurrentlyEnrolledInSimilar(Section section) {
		boolean foundMatch = false;
		return foundMatch;
	}

	public void displayCourseSchedule() {
		System.out.println("Course Schedule for " + this.getName());
	}

	public void printTranscript() {
		this.getTranscript().display();
	}

	public String toString() {
		return this.getName() + " (" + this.getSsn() + ") [" + this.getDegree() + " - " + this.getMajor() + "]";
	}
}