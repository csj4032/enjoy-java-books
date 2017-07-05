package ch15.srs;

import lombok.Getter;
import lombok.Setter;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

@Getter
@Setter
public class Section {

	private int sectionNo;
	private char dayOfWeek;
	private String timeOfDay;
	private String room;
	private int seatingCapacity;
	private Course representedCourse;
	private ScheduleOfClasses offeredIn;
	private Professor instructor;
	private Hashtable enrolledStudents;
	private Hashtable assignedGrades;

	public static final int SUCCESSFULLY_ENROLLED = 0;
	public static final int SECTION_FULL = 1;
	public static final int PREREQ_NOT_SATISFIED = 2;
	public static final int PREVIOUSLY_ENROLLED = 3;

	public Section(int sNo, char day, String time, Course course, String room, int capacity) {
		setSectionNo(sNo);
		setDayOfWeek(day);
		setTimeOfDay(time);
		setRepresentedCourse(course);
		setRoom(room);
		setSeatingCapacity(capacity);
		setInstructor(null);

		enrolledStudents = new Hashtable();
		assignedGrades = new Hashtable();
	}

	public String toString() {
		return getRepresentedCourse().getCourseNo() + " - " + getSectionNo() + " - " + getDayOfWeek() + " - " + getTimeOfDay();
	}

	public String getFullSectionNo() {
		return getRepresentedCourse().getCourseNo() + " - " + getSectionNo();
	}

	public int enroll(Student s) {

		Transcript transcript = s.getTranscript();

		if (s.isEnrolledIn(this) || transcript.verifyCompletion(this.getRepresentedCourse())) return PREVIOUSLY_ENROLLED;

		Course c = getRepresentedCourse();
		if (c.hasPrerequisites()) {
			List<Course> courses = c.getPrerequisites();
			for (Course course : courses) {
				if (!transcript.verifyCompletion(course)) return PREREQ_NOT_SATISFIED;
			}
		}

		if (!confirmSeatAvailability()) return SECTION_FULL;

		enrolledStudents.put(s.getSsn(), s);
		s.addSection(this);
		return SUCCESSFULLY_ENROLLED;
	}

	private boolean confirmSeatAvailability() {
		return (enrolledStudents.size() < getSeatingCapacity()) ? true : false;
	}

	public boolean drop(Student s) {
		if (!s.isEnrolledIn(this)) {
			return false;
		} else {
			enrolledStudents.remove(s.getSsn());
			s.dropSection(this);
			return true;
		}
	}

	public int getTotalEnrollment() {
		return enrolledStudents.size();
	}

	public void display() {
		System.out.println("Section Information:");
		System.out.println("\tSemester:  " + getOfferedIn().getSemester());
		System.out.println("\tCourse No.:  " + getRepresentedCourse().getCourseNo());
		System.out.println("\tSection No:  " + getSectionNo());
		System.out.println("\tOffered:  " + getDayOfWeek() + " at " + getTimeOfDay());
		System.out.println("\tIn Room:  " + getRoom());
		if (getInstructor() != null) System.out.println("\tProfessor:  " + getInstructor().getName());
		displayStudentRoster();
	}

	public void displayStudentRoster() {
		System.out.print("\tTotal of " + getTotalEnrollment() + " students enrolled");

		if (getTotalEnrollment() == 0) System.out.println(".");
		else System.out.println(", as follows:");

		Enumeration e = enrolledStudents.elements();
		while (e.hasMoreElements()) {
			Student s = (Student) e.nextElement();
			System.out.println("\t\t" + s.getName());
		}
	}

	public String getGrade(Student s) {

		TranscriptEntry te = (TranscriptEntry) assignedGrades.get(s);
		if (te != null) {
			String grade = te.getGrade();
			return grade;
		} else return null;
	}

	public boolean postGrade(Student s, String grade) {

		if (assignedGrades.get(s) != null) return false;

		TranscriptEntry te = new TranscriptEntry(s, grade, this);

		assignedGrades.put(s, te);

		return true;
	}

	public boolean isSectionOf(Course c) {
		return c == representedCourse ? true : false;
	}
}