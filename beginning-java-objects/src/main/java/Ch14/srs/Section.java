package Ch14.srs;

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

		enrolledStudents = new HashMap<String, Student>();
		assignedGrades = new HashMap<Student, TranscriptEntry>();
	}

	public String getFullSectionNo() {
		return getRepresentedCourse().getCourseNo() + " - " + getSectionNo();
	}

	public String toString() {
		return getRepresentedCourse().getCourseNo() + " - " + getSectionNo() + " - " + getDayOfWeek() + " - " + getTimeOfDay();
	}
}