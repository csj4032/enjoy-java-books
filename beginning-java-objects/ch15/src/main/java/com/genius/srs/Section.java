package com.genius.srs;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
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
	private Map<String, Student> enrolledStudents;
	private Map<Student, TranscriptEntry> assignedGrades;

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

		enrolledStudents = new HashMap<>();
		assignedGrades = new HashMap<>();
	}

	public String toString() {
		return getRepresentedCourse().getCourseNo() + " - " + getSectionNo() + " - " + getDayOfWeek() + " - " + getTimeOfDay();
	}

	public String getFullSectionNo() {
		return getRepresentedCourse().getCourseNo() + " - " + getSectionNo();
	}

	public int enroll(Student student) {
		Transcript transcript = student.getTranscript();
		if (student.isEnrolledIn(this) || transcript.verifyCompletion(this.getRepresentedCourse()))
			return PREVIOUSLY_ENROLLED;
		Course c = getRepresentedCourse();
		if (c.hasPrerequisites()) {
			List<Course> courses = c.getPrerequisites();
			for (Course course : courses) {
				if (!transcript.verifyCompletion(course)) return PREREQ_NOT_SATISFIED;
			}
		}

		if (!confirmSeatAvailability()) return SECTION_FULL;

		enrolledStudents.put(student.getSsn(), student);
		student.addSection(this);
		return SUCCESSFULLY_ENROLLED;
	}

	private boolean confirmSeatAvailability() {
		return enrolledStudents.size() < getSeatingCapacity();
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
		log.info("Section Information:");
		log.info("\tSemester:  " + getOfferedIn().getSemester());
		log.info("\tCourse No.:  " + getRepresentedCourse().getCourseNo());
		log.info("\tSection No:  " + getSectionNo());
		log.info("\tOffered:  " + getDayOfWeek() + " at " + getTimeOfDay());
		log.info("\tIn Room:  " + getRoom());
		if (getInstructor() != null) log.info("\tProfessor:  " + getInstructor().getName());
		displayStudentRoster();
	}

	public void displayStudentRoster() {
		log.info("\tTotal of " + getTotalEnrollment() + " students enrolled");
		if (enrolledStudents.isEmpty()) {
			log.info(".");
		} else {
			log.info(", as follows:");
		}
		enrolledStudents.values().forEach(e -> log.info("\t\t" + e.getName()));
	}

	public String getGrade(Student s) {
		TranscriptEntry te = assignedGrades.get(s);
		if (te != null) {
			return te.getGrade();
		} else return null;
	}

	public boolean postGrade(Student s, String grade) {
		if (assignedGrades.get(s) != null) return false;
		TranscriptEntry te = new TranscriptEntry(s, grade, this);
		assignedGrades.put(s, te);
		return true;
	}

	public boolean isSectionOf(Course c) {
		return c == representedCourse;
	}
}