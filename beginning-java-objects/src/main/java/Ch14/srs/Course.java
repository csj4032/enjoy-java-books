package ch14.srs;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;

@Data
@ToString
public class Course {

	private String courseNo;
	private String courseName;
	private double credits;
	private ArrayList<Section> offeredAsSection;
	private ArrayList<Course> prerequisites;

	public Course(String cNo, String cName, double credits) {
		setCourseNo(cNo);
		setCourseName(cName);
		setCredits(credits);

		offeredAsSection = new ArrayList<>();
		prerequisites = new ArrayList<>();
	}

	public void display() {
		System.out.println("Course Information:");
		System.out.println("\tCourse No.:  " + getCourseNo());
		System.out.println("\tCourse Name:  " + getCourseName());
		System.out.println("\tCredits:  " + getCredits());
		System.out.println("\tPrerequisite Courses:");

		for (Course c : prerequisites) {
			System.out.println("\t\t" + c.toString());
		}

		System.out.print("\tOffered As Section(s):  ");
		for (Section s : offeredAsSection) {
			System.out.print(s.getSectionNo() + " ");
		}

		System.out.println();
	}

	public void addPrerequisite(Course c) {
		prerequisites.add(c);
	}

	public boolean hasPrerequisites() {
		if (prerequisites.size() > 0) return true;
		else return false;
	}

	public Collection<Course> getPrerequisites() {
		return prerequisites;
	}

	public Section scheduleSection(char day, String time, String room, int capacity) {
		Section s = new Section(offeredAsSection.size() + 1, day, time, this, room, capacity);
		addSection(s);
		return s;
	}

	public void addSection(Section s) {
		offeredAsSection.add(s);
	}

	public String toString() {
		return getCourseNo() + ":  " + getCourseName();
	}
}