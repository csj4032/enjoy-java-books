package com.genius.srs;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentRegistrationSystem {

	public static final String LINE = "====================";
	public static Faculty faculty;
	public static CourseCatalog courseCatalog;
	public static ScheduleOfClasses scheduleOfClasses;

	public static void main(String[] args) {
		try {
			courseCatalog = DataAccess.initializeCourseCatalog();
			scheduleOfClasses = DataAccess.initializeScheduleOfClasses("SP2001");
			faculty = DataAccess.initializeFaculty();
		} catch (InitializationException e) {
			log.error(e.getMessage());
		}

		Student s1;
		Student s2;
		Student s3;

		try {
			s1 = DataAccess.initializeStudent("111-11-1111");
			s2 = DataAccess.initializeStudent("222-22-2222");
			s3 = DataAccess.initializeStudent("333-33-3333");

			log.info(LINE);
			log.info("Course Catalog:");
			log.info(LINE);
			courseCatalog.display();

			log.info(LINE);
			log.info("Schedule of Classes:");
			log.info(LINE);
			scheduleOfClasses.display();

			log.info(LINE);
			log.info("Professor Information:");
			log.info(LINE);
			faculty.display();

			log.info(LINE);
			log.info("Student Information:");
			log.info(LINE);
			s1.display();
			s2.display();
			s3.display();

		} catch (InvalidStudentException e) {
			log.error(e.getLocalizedMessage());
		}


	}
}