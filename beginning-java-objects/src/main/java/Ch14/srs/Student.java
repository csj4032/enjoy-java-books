package ch14.srs;

import java.util.ArrayList;
import java.util.Collection;

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

	public Student(String ssn) {
		this("TBD", ssn, "TBD", "TBD");
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getMajor() {
		return major;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getDegree() {
		return degree;
	}

	public void setTranscript(Transcript t) {
		transcript = t;
	}

	public Transcript getTranscript() {
		return transcript;
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

	public boolean isEnrolledIn(Section section) {
		return attends.contains(section) ? true : false;
	}


	public void displayCourseSchedule() {
		System.out.println("Course Schedule for " + this.getName());
		for (Section s : attends) {
			if (s.getGrade(this) == null) {
				System.out.println("\tCourse No.:  " + s.getRepresentedCourse().getCourseNo());
				System.out.println("\tSection No.:  " + s.getSectionNo());
				System.out.println("\tCourse Name:  " + s.getRepresentedCourse().getCourseName());
				System.out.println("\tMeeting Day and Time Held:  " + s.getDayOfWeek() + " - " + s.getTimeOfDay());
				System.out.println("\tRoom Location:  " + s.getRoom());
				System.out.println("\tProfessor's Name:  " + s.getInstructor().getName());
				System.out.println("\t-----");
			}
		}
	}

	public boolean isCurrentlyEnrolledInSimilar(Section section) {
		boolean foundMatch = false;

		Course c1 = section.getRepresentedCourse();

		for (Section section1 : attends) {
			Course c2 = section1.getRepresentedCourse();
			if (c1 == c2) {
				if (section1.getGrade(this) == null) {
					foundMatch = true;
					break;
				}
			}
		}

		return foundMatch;
	}

	public void addSection(Section s) {
		attends.add(s);
	}

	public void dropSection(Section s) {
		attends.remove(s);
	}

	public void printTranscript() {
		this.getTranscript().display();
	}

	public Collection<Section> getEnrolledSections() {
		return attends;
	}

	public String toString() {
		return this.getName() + " (" + this.getSsn() + ") [" + this.getDegree() + " - " + this.getMajor() + "]";
	}
}