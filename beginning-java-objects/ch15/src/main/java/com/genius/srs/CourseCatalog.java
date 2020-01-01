package com.genius.srs;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Slf4j
public class CourseCatalog {

	private Map<String, Course> courses;

	public CourseCatalog() {
		courses = new HashMap<>();
	}

	public void display() {
		log.info("Course Catalog:");
		courses.values().forEach(Course::display);
	}

	public void addCourse(Course c) {
		String key = c.getCourseNo();
		courses.put(key, c);
	}

	public Course findCourse(String courseNo) {
		return courses.get(courseNo);
	}

	public boolean isEmpty() {
		return courses.isEmpty();
	}
}
