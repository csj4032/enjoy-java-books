package com.genius.srs;

public class StudentRegistrationSystem {

	public static Faculty faculty = new Faculty();
	public static CourseCatalog courseCatalog = new CourseCatalog();
	public static ScheduleOfClasses scheduleOfClasses = new ScheduleOfClasses("SP2001");

	public static void main(String[] args) {
		faculty.initializeObjects("/data/Faculty.dat", true);
		courseCatalog.initializeObjects("/data/CourseCatalog.dat", true);
		scheduleOfClasses.initializeObjects("/data/SoC_SP2001.dat", true);

		Student s1 = new Student("111-11-1111");
		Student s2 = new Student("222-22-2222");
		Student s3 = new Student("333-33-3333");

		courseCatalog.initializeObjects("/data/Prerequisites.dat", false);
		faculty.initializeObjects("/data/TeachingAssignments.dat", false);

		System.out.println("====================");
		System.out.println("Course Catalog:");
		System.out.println("====================");
		System.out.println("");
		courseCatalog.display();

		System.out.println("====================");
		System.out.println("Schedule of Classes:");
		System.out.println("====================");
		System.out.println("");
		scheduleOfClasses.display();

		System.out.println("======================");
		System.out.println("Professor Information:");
		System.out.println("======================");
		System.out.println("");
		faculty.display();

		System.out.println("====================");
		System.out.println("Student Information:");
		System.out.println("====================");
		System.out.println("");
		s1.display();
		System.out.println("");
		s2.display();
		System.out.println("");
		s3.display();
	}
}