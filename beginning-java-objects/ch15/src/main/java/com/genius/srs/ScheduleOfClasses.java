package com.genius.srs;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Getter
@Setter
public class ScheduleOfClasses {
	private String semester;
	private Map<String, Section> sections;

	public ScheduleOfClasses(String semester) {
		setSemester(semester);
		sections = new HashMap<>();
	}

	public ScheduleOfClasses() {
		this("");
	}

	public void display() {
		log.info("Schedule of Classes for " + getSemester());
		sections.values().forEach(Section::display);
	}

	public void addSection(Section s) {
		String key = s.getRepresentedCourse().getCourseNo() + " - " + s.getSectionNo();
		sections.put(key, s);
		s.setOfferedIn(this);
	}

	public Section findSection(String fullSectionNo) {
		return sections.get(fullSectionNo);
	}

	public boolean isEmpty() {
		return sections.isEmpty();
	}
}
