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

		if (!this.confirmSeatAvailability()) {
			return EnrollmentStatus.secFull;
		}

		return EnrollmentStatus.success;
	}

	private boolean confirmSeatAvailability() {
		if (enrolledStudents.size() < getSeatingCapacity()) return true;
		else return false;
	}

	public boolean isSectionOf(Course course) {
		if (course == representedCourse) return true;
		else return false;
	}

	public String toString() {
		return getRepresentedCourse().getCourseNo() + " - " + getSectionNo() + " - " + getDayOfWeek() + " - " + getTimeOfDay();
	}
}