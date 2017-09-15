package ch15.srs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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
		offeredAsSection = new ArrayList();
		prerequisites = new ArrayList();
	}

	public void display() {
		System.out.println("Course Information:");
		System.out.println("\tCourse No.:  " + getCourseNo());
		System.out.println("\tCourse Name:  " + getCourseName());
		System.out.println("\tCredits:  " + getCredits());
		System.out.println("\tPrerequisite Courses:");

		if (hasPrerequisites()) {
			List<Course> courses = getPrerequisites();
			for (Course course : courses) {
				System.out.println("\t\t" + course.toString());
			}
		} else {
			System.out.println("\t\t(none)");
		}

		System.out.print("\tOffered As Section(s):  ");

		for (int i = 0; i < offeredAsSection.size(); i++) {
			Section s = offeredAsSection.get(i);
			System.out.print(s.getSectionNo() + " ");
		}

		System.out.println("");
	}

	public String toString() {
		return getCourseNo() + ":  " + getCourseName();
	}

	public void addPrerequisite(Course c) {
		prerequisites.add(c);
	}

	public boolean hasPrerequisites() {
		if (prerequisites.size() > 0) return true;
		else return false;
	}

	public List getPrerequisites() {
		return prerequisites;
	}

	public Section scheduleSection(int secNo, char day, String time, String room, int capacity) {
		Section s = new Section(secNo, day, time, this, room, capacity);
		offeredAsSection.add(s);
		return s;
	}
}