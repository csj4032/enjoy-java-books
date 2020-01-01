package com.genius.srs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Setter
@ToString
public class Professor extends Person {

	private String title;
	private String department;
	private List<Section> teaches;

	public Professor(String name, String ssn, String title, String department) {
		super(name, ssn);
		setTitle(title);
		setDepartment(department);
		teaches = new ArrayList<>();
	}

	@Override
	public void display() {
		super.display();
		log.info("Professor-Specific Information:");
		log.info("\tTitle:  " + getTitle());
		log.info("\tTeaches for Dept.:  " + getDepartment());
		displayTeachingAssignments();
	}

	private void displayTeachingAssignments() {
		log.info("Teaching Assignments for " + getName() + ":");
		if (teaches.isEmpty()) {
			log.info("\t(none)");
		} else {
			for (Section section : teaches) {
				log.info("\tCourse No.:  " + section.getRepresentedCourse().getCourseNo());
				log.info("\tSection No.:  " + section.getSectionNo());
				log.info("\tCourse Name:  " + section.getRepresentedCourse().getCourseName());
				log.info("\tDay and Time:  " + section.getDayOfWeek() + " - " + section.getTimeOfDay());
				log.info("\t-----");
			}
		}
	}

	public String toString() {
		return getName() + " (" + getTitle() + ", " + getDepartment() + ")";
	}

	public void agreeToTeach(Section section) {
		teaches.add(section);
		section.setInstructor(this);
	}
}
