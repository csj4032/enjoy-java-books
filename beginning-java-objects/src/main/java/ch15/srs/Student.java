package ch15.srs;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Student extends Person {

	private String major;
	private String degree;
	private Transcript transcript;
	private List attends;

	public Student(String ssn) {

		String line;
		String pathToFile = "data" + ssn + ".dat";

		try (BufferedReader bIn = new BufferedReader(new FileReader(pathToFile));) {

			line = bIn.readLine();
			if (line != null) parseData(line);

			attends = new ArrayList();

			line = bIn.readLine();

			while (line != null) {
				parseData2(line);
				line = bIn.readLine();
			}

			bIn.close();
		} catch (FileNotFoundException f) {

		} catch (IOException i) {

		}
		setTranscript(new Transcript(this));
	}

	public Student() {
		super("???", "???");
		setMajor("???");
		setDegree("???");
		setTranscript(new Transcript(this));
		attends = new ArrayList();
	}

	public void display() {
		super.display();
		System.out.println("Student-Specific Information:");
		System.out.println("\tMajor:  " + getMajor());
		System.out.println("\tDegree:  " + getDegree());
		displayCourseSchedule();
		printTranscript();
	}

	public String toString() {
		return getName() + " (" + getSsn() + ") [" + getDegree() + " - " + getMajor() + "]";
	}

	public void displayCourseSchedule() {

		System.out.println("Course Schedule for " + getName());

		if (attends.size() == 0) System.out.println("\t(none)");

		else for (int i = 0; i < attends.size(); i++) {
			Section s = (Section) attends.get(i);

			if (s.getGrade(this) == null) {
				System.out.println("\tCourse No.:  " + s.getRepresentedCourse().getCourseNo());
				System.out.println("\tSection No.:  " + s.getSectionNo());
				System.out.println("\tCourse Name:  " + s.getRepresentedCourse().getCourseName());
				System.out.println("\tMeeting Day and Time Held:  " + s.getDayOfWeek() + " - " + s.getTimeOfDay());
				System.out.println("\tRoom Location:  " + s.getRoom());
				System.out.println("\tProfessor's Name:  " + s.getInstructor().getName());
				System.out.println("\t-----");
			}
		}
	}

	public void addSection(Section s) {
		attends.add(s);
	}

	public void dropSection(Section s) {
		attends.remove(s);
	}

	public boolean isEnrolledIn(Section s) {
		if (attends.contains(s)) return true;
		else return false;
	}

	public void printTranscript() {
		getTranscript().display();
	}

	public void parseData(String line) {
		String restOfLine = line;
		int index = restOfLine.indexOf("\t");
		setSsn(restOfLine.substring(0, index));
		restOfLine = restOfLine.substring(index + 1);
		index = restOfLine.indexOf("\t");
		setName(restOfLine.substring(0, index));
		restOfLine = restOfLine.substring(index + 1);
		index = restOfLine.indexOf("\t");
		setMajor(restOfLine.substring(0, index));
		setDegree(restOfLine.substring(index + 1));
	}

	public void parseData2(String line) {
		String fullSectionNo = line.trim();
		Section s = StudentRegistrationSystem.scheduleOfClasses.findSection(fullSectionNo);
		s.enroll(this);
	}

	public boolean successfullyInitialized() {
		if (getName().equals("???")) return false;
		else return true;
	}

	public boolean persist() {
		PrintWriter pw = null;
		try (FileOutputStream fos = new FileOutputStream(getSsn() + ".dat");) {
			pw = new PrintWriter(fos);
			pw.println(getSsn() + "\t" + getName() + "\t" + getMajor() + "\t" + getDegree());

			for (int i = 0; i < attends.size(); i++) {
				Section s = (Section) attends.get(i);
				pw.println(s.getFullSectionNo());
			}

			pw.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
}