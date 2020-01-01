package com.genius.srs;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Setter
public class Student extends Person {

	private String major;
	private String degree;
	private Transcript transcript;
	private List<Section> attends;

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

	@Override
	public void display() {
		super.display();
		log.info("Student-Specific Information:");
		log.info("\tMajor:  " + getMajor());
		log.info("\tDegree:  " + getDegree());
		displayCourseSchedule();
		printTranscript();
	}

	public String toString() {
		return getName() + " (" + getSsn() + ") [" + getDegree() + " - " + getMajor() + "]";
	}

	public void displayCourseSchedule() {
		log.info("Course Schedule for " + getName());
		if (attends.isEmpty()) {
			log.info("\t(none)");
		} else {
			attends.forEach(section -> {
				if (section.getGrade(this) == null) {
					log.info("\tCourse No.:  " + section.getRepresentedCourse().getCourseNo());
					log.info("\tSection No.:  " + section.getSectionNo());
					log.info("\tCourse Name:  " + section.getRepresentedCourse().getCourseName());
					log.info("\tMeeting Day and Time Held:  " + section.getDayOfWeek() + " - " + section.getTimeOfDay());
					log.info("\tRoom Location:  " + section.getRoom());
					log.info("\tProfessor's Name:  " + section.getInstructor().getName());
					log.info("\t-----");
				}
			});
		}
	}

	public void addSection(Section s) {
		attends.add(s);
	}

	public void dropSection(Section s) {
		attends.remove(s);
	}

	public boolean isEnrolledIn(Section section) {
		return attends.contains(section);
	}

	public void printTranscript() {
		getTranscript().display();
	}

	public boolean successfullyInitialized() {
		return getName().equals("???");
	}

	public List<Section> getEnrolledSections() {
		return this.attends;
	}
}