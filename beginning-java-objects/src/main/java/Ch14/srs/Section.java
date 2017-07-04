package ch14.srs;

import lombok.Data;

import java.util.HashMap;

@Data
public class Section {
	private int sectionNo;
	private char dayOfWeek;
	private String timeOfDay;
	private String room;
	private int seatingCapacity;
	private Course representedCourse;
	private ScheduleOfClasses offeredIn;
	private Professor instructor;
	private HashMap<String, Student> enrolledStudents;
	private HashMap<Student, TranscriptEntry> assignedGrades;

	public Section(int sNo, char day, String time, Course course, String room, int capacity) {
		setSectionNo(sNo);
		setDayOfWeek(day);
		setTimeOfDay(time);
		setRepresentedCourse(course);
		setRoom(room);
		setSeatingCapacity(capacity);

		setInstructor(null);

		enrolledStudents = new HashMap<>();
		assignedGrades = new HashMap<>();
	}

	public String getFullSectionNo() {
		return getRepresentedCourse().getCourseNo() + " - " + getSectionNo();
	}

	public EnrollmentStatus enroll(Student student) {
		Transcript transcript = student.getTranscript();

		if (student.isCurrentlyEnrolledInSimilar(this) || transcript.verifyCompletion(this.getRepresentedCourse())) {
			return EnrollmentStatus.prevEnroll;
		}

		Course course = this.getRepresentedCourse();

		if (course.hasPrerequisites()) {
			for (Course pre : course.getPrerequisites()) {
				if (!transcript.verifyCompletion(pre)) {
					return EnrollmentStatus.prereq;
				}
			}
		}

		if (!this.confirmSeatAvailability()) return EnrollmentStatus.secFull;

		enrolledStudents.put(student.getSsn(), student);
		student.addSection(this);

		return EnrollmentStatus.success;
	}

	private boolean confirmSeatAvailability() {
		return (enrolledStudents.size() < getSeatingCapacity()) ? true : false;
	}

	public boolean drop(Student student) {
		if (student.isEnrolledIn(this)) {
			return false;
		} else {
			enrolledStudents.remove(student.getSsn());
			student.dropSection(this);
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

		if (getTotalEnrollment() == 0) {
			System.out.println(".");
		} else {
			System.out.println(", as follows:");
		}

		for (Student s : enrolledStudents.values()) {
			System.out.println("\t\t" + s.getName());
		}
	}

	public String getGrade(Student student) {
		String grade = null;

		TranscriptEntry transcriptEntry = assignedGrades.get(student);

		if (transcriptEntry != null) grade = transcriptEntry.getGrade();

		return grade;
	}

	public boolean postGrade(Student student, String grade) {
		if (!TranscriptEntry.validateGrade(grade)) return false;
		if (assignedGrades.get(student) != null) return false;
		TranscriptEntry transcriptEntry = new TranscriptEntry(student, grade, this);
		assignedGrades.put(student, transcriptEntry);
		return true;
	}

	public boolean isSectionOf(Course course) {
		return (course == representedCourse) ? true : false;
	}

	public String toString() {
		return getRepresentedCourse().getCourseNo() + " - " + getSectionNo() + " - " + getDayOfWeek() + " - " + getTimeOfDay();
	}
}