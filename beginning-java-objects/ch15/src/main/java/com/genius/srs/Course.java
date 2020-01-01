package com.genius.srs;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

@Slf4j
@Getter
@Setter
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
		log.info("Course Information:");
		log.info("\tCourse No.:  " + getCourseNo());
		log.info("\tCourse Name:  " + getCourseName());
		log.info("\tCredits:  " + getCredits());
		log.info("\tPrerequisite Courses:");
		if (hasPrerequisites()) {
			List<Course> courses = getPrerequisites();
			for (Course course : courses) log.info("\t\t" + course.toString());
		} else {
			log.info("\t\t(none)");
		}
		log.info("\tOffered As Section(s): {}", offeredAsSection.stream().map(s -> s.getSectionNo() + " ").collect(joining()));
	}

	public String toString() {
		return getCourseNo() + ":  " + getCourseName();
	}

	public void addPrerequisite(Course c) {
		prerequisites.add(c);
	}

	public boolean hasPrerequisites() {
		return !prerequisites.isEmpty();
	}

	public List<Course> getPrerequisites() {
		return prerequisites;
	}

	public Section scheduleSection(int secNo, char day, String time, String room, int capacity) {
		Section s = new Section(secNo, day, time, this, room, capacity);
		offeredAsSection.add(s);
		return s;
	}

	public void addSection(Section section) {
		offeredAsSection.add(section);
	}
}