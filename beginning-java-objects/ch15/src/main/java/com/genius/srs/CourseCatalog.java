package com.genius.srs;

import lombok.Getter;
import lombok.Setter;

import java.util.Enumeration;
import java.util.Hashtable;

@Getter
@Setter
public class CourseCatalog extends CollectionWrapper {

	private Hashtable courses;

	public CourseCatalog() {
		courses = new Hashtable();
	}

	public void display() {
		System.out.println("Course Catalog:");
		System.out.println("");

		Enumeration e = courses.elements();

		while (e.hasMoreElements()) {
			Course c = (Course) e.nextElement();
			c.display();
			System.out.println("");
		}
	}

	public void addCourse(Course c) {
		String key = c.getCourseNo();
		courses.put(key, c);
	}

	public void parseData(String line) {
		String restOfLine = line;
		int index = restOfLine.indexOf("\t");
		String courseNo = restOfLine.substring(0, index);
		restOfLine = restOfLine.substring(index + 1);
		index = restOfLine.indexOf("\t");
		String courseName = restOfLine.substring(0, index);
		String creditValue = restOfLine.substring(index + 1);

		double credits = Double.parseDouble(creditValue);

		Course c = new Course(courseNo, courseName, credits);
		addCourse(c);
	}

	public Course findCourse(String courseNo) {
		return (Course) courses.get(courseNo);
	}

	public void parseData2(String line) {

		String restOfLine = line;
		int index = restOfLine.indexOf("\t");
		String courseNoA = restOfLine.substring(0, index);
		String courseNoB = restOfLine.substring(index + 1);

		Course a = findCourse(courseNoA);
		Course b = findCourse(courseNoB);
		if (a != null && b != null) b.addPrerequisite(a);
	}

	public static void main(String[] args) {
		CourseCatalog cc = new CourseCatalog();
		cc.initializeObjects("CourseCatalog.dat", true);
		cc.initializeObjects("Prerequisites.dat", false);
		cc.display();
	}
}
