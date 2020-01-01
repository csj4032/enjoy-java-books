package com.genius.srs;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Collection;
import java.util.Properties;
import java.util.StringTokenizer;

@Slf4j
public class DataAccess {

	public static final String FILE_FORMAT_ERROR_ON_RECORD = "File format error on record |";
	public static final String ERROR_ACCESSING_FILE = "Error accessing file ";
	public static final String IN_FILE = "in file ";

	private DataAccess() {

	}

	private static final String FILE_NAMES = "fileNames.properties";
	private static String scheduleFileName;
	private static String facultyFileName;
	private static String assignmentsFileName;
	private static String courseFileName;
	private static String prereqFileName;

	static {
		try {
			Properties fileNames = new Properties();
			fileNames.load(new FileInputStream(ClassLoader.getSystemClassLoader().getResource(FILE_NAMES).getFile()));
			facultyFileName = fileNames.getProperty("facultyFile");
			assignmentsFileName = fileNames.getProperty("assignmentsFile");
			courseFileName = fileNames.getProperty("courseFile");
			prereqFileName = fileNames.getProperty("prereqFile");
			scheduleFileName = fileNames.getProperty("scheduleFile");
		} catch (IOException e) {
			log.error("ERROR: unable to read fileNames.properties file -- GOODBYE!");
			System.exit(0);
		}
	}

	public static Faculty initializeFaculty() throws InitializationException {
		Faculty faculty = new Faculty();
		String line;
		try (BufferedReader bIn = new BufferedReader(new FileReader(ClassLoader.getSystemClassLoader().getResource(facultyFileName).getFile()))) {
			line = bIn.readLine();
			while (line != null) {
				StringTokenizer st = new StringTokenizer(line, "\t");
				if (st.countTokens() != 4) {
					throw new InitializationException(FILE_FORMAT_ERROR_ON_RECORD + line + "| -- should have 4 tokens -- in file " + facultyFileName);
				} else {
					String name = st.nextToken();
					String ssn = st.nextToken();
					String title = st.nextToken();
					String dept = st.nextToken();
					Professor p = new Professor(name, ssn, title, dept);
					faculty.addProfessor(p);
				}
				line = bIn.readLine();
			}
		} catch (IOException i) {
			throw new InitializationException(ERROR_ACCESSING_FILE + facultyFileName);
		}

		try (BufferedReader bIn = new BufferedReader(new FileReader(ClassLoader.getSystemClassLoader().getResource(assignmentsFileName).getFile()))) {
			line = bIn.readLine();
			while (line != null) {
				StringTokenizer st = new StringTokenizer(line, "\t");
				if (st.countTokens() != 2) {
					throw new InitializationException(FILE_FORMAT_ERROR_ON_RECORD + line + "| -- should have 2 tokens -- in file " + assignmentsFileName);
				} else {
					String ssn = st.nextToken();
					String fullSectionNo = st.nextToken();
					Professor p = faculty.findProfessor(ssn);
					Section s = StudentRegistrationSystem.scheduleOfClasses.findSection(fullSectionNo);
					if (p != null && s != null) p.agreeToTeach(s);
				}
				line = bIn.readLine();
			}
		} catch (IOException i) {
			throw new InitializationException(ERROR_ACCESSING_FILE + assignmentsFileName);
		}
		if (faculty.isEmpty()) {
			throw new InitializationException("Error initializing faculty " + "information");
		}
		return faculty;
	}

	public static CourseCatalog initializeCourseCatalog() throws InitializationException {
		CourseCatalog catalog = new CourseCatalog();
		String line;
		try (BufferedReader bIn = new BufferedReader(new FileReader(ClassLoader.getSystemClassLoader().getResource(courseFileName).getFile()))) {
			line = bIn.readLine();
			while (line != null) {
				StringTokenizer st = new StringTokenizer(line, "\t");
				if (st.countTokens() != 3) {
					throw new InitializationException(FILE_FORMAT_ERROR_ON_RECORD + line + "| -- should have 3 tokens -- in file " + courseFileName);
				} else {
					String courseNo = st.nextToken();
					String courseName = st.nextToken();
					String creditValue = st.nextToken();
					double credits = Double.parseDouble(creditValue);
					Course c = new Course(courseNo, courseName, credits);
					catalog.addCourse(c);
				}
				line = bIn.readLine();
			}
		} catch (IOException | NumberFormatException i) {
			throw new InitializationException(ERROR_ACCESSING_FILE + courseFileName);
		}

		try (BufferedReader bIn = new BufferedReader(new FileReader(ClassLoader.getSystemClassLoader().getResource(prereqFileName).getFile()))) {
			line = bIn.readLine();
			while (line != null) {
				StringTokenizer st = new StringTokenizer(line, "\t");
				if (st.countTokens() != 2) {
					throw new InitializationException(FILE_FORMAT_ERROR_ON_RECORD + line + "| -- should have 2 tokens -- in file " + prereqFileName);
				} else {
					String courseNoA = st.nextToken();
					String courseNoB = st.nextToken();
					Course a = catalog.findCourse(courseNoA);
					Course b = catalog.findCourse(courseNoB);
					if (a != null && b != null) {
						b.addPrerequisite(a);
					}
				}
				line = bIn.readLine();
			}
		} catch (IOException i) {
			throw new InitializationException(ERROR_ACCESSING_FILE + prereqFileName);
		}
		if (catalog.isEmpty()) {
			throw new InitializationException("Error initializing course " + "catalog information");
		}
		return catalog;
	}

	public static ScheduleOfClasses initializeScheduleOfClasses(String semester) throws InitializationException {
		ScheduleOfClasses soc = new ScheduleOfClasses(semester);
		String line;
		try (BufferedReader bIn = new BufferedReader(new FileReader(ClassLoader.getSystemClassLoader().getResource(scheduleFileName).getFile()))) {
			line = bIn.readLine();
			while (line != null) {
				StringTokenizer st = new StringTokenizer(line, "\t");
				if (st.countTokens() != 6) {
					throw new InitializationException(FILE_FORMAT_ERROR_ON_RECORD + line + "| -- should have 6 tokens -- " + IN_FILE + scheduleFileName);
				} else {
					String courseNo = st.nextToken();
					String sectionNumber = st.nextToken();
					int sectionNo = Integer.parseInt(sectionNumber);
					String dayOfWeek = st.nextToken();
					String timeOfDay = st.nextToken();
					String room = st.nextToken();
					String capacityValue = st.nextToken();
					int capacity = Integer.parseInt(capacityValue);
					Course c = StudentRegistrationSystem.courseCatalog.findCourse(courseNo);
					Section s = new Section(sectionNo, dayOfWeek.charAt(0), timeOfDay, c, room, capacity);
					soc.addSection(s);
					c.addSection(s);
				}
				line = bIn.readLine();
			}
		} catch (IOException | NumberFormatException i) {
			throw new InitializationException(ERROR_ACCESSING_FILE + scheduleFileName);
		}
		if (soc.isEmpty()) {
			throw new InitializationException("Error initializing " + "schedule of classes information");
		}
		return soc;
	}

	public static Student initializeStudent(String sId) throws InvalidStudentException {
		Student s = new Student("?");
		String line;
		String pathToFile = "data/" + sId + ".dat";
		try (BufferedReader bIn = new BufferedReader(new FileReader(ClassLoader.getSystemClassLoader().getResource(pathToFile).getFile()))) {
			line = bIn.readLine();
			if (line == null) {
				throw new InvalidStudentException("Improperly formatted file:  " + pathToFile);
			}
			StringTokenizer st = new StringTokenizer(line, "\t");
			if (st.countTokens() != 4) {
				throw new InvalidStudentException("Improperly formatted " + "record |" + line + "| in file " + pathToFile);
			}
			s.setSsn(st.nextToken());
			s.setName(st.nextToken());
			s.setMajor(st.nextToken());
			s.setDegree(st.nextToken());
			line = bIn.readLine();
			while (line != null) {
				String fullSectionNo = line.trim();
				Section sec = StudentRegistrationSystem.scheduleOfClasses.findSection(fullSectionNo);
				if (sec != null) sec.enroll(s);
				line = bIn.readLine();
			}
		} catch (IOException i) {
			throw new InvalidStudentException(ERROR_ACCESSING_FILE + pathToFile);
		}
		return s;
	}

	public static void persistStudent(Student s) throws StudentPersistenceException {
		PrintWriter pw = null;
		String pathToFile = s.getSsn() + ".dat";
		try (FileOutputStream fos = new FileOutputStream(ClassLoader.getSystemClassLoader().getResource(pathToFile).getFile())) {
			pw = new PrintWriter(fos);
			pw.println(s.getSsn() + "\t" + s.getName() + "\t" + s.getMajor() + "\t" + s.getDegree());
			Collection<Section> sections = s.getEnrolledSections();
			for (Section sec : sections) {
				pw.println(sec.getFullSectionNo());
			}
		} catch (IOException e) {
			throw new StudentPersistenceException("Error saving student " + "information to file " + pathToFile);
		}
	}
}
