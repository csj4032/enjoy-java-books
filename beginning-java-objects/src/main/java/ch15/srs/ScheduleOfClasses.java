package ch15.srs;

import lombok.Getter;
import lombok.Setter;

import java.util.Enumeration;
import java.util.Hashtable;

@Getter
@Setter
public class ScheduleOfClasses  extends CollectionWrapper {
	private String semester;
	private Hashtable sections;

	public ScheduleOfClasses(String semester) {
		setSemester(semester);
		sections = new Hashtable();
	}

	public void display() {
		System.out.println("Schedule of Classes for " + getSemester());
		System.out.println("");

		Enumeration e = sections.elements();

		while (e.hasMoreElements()) {
			Section s = (Section) e.nextElement();
			s.display();
			System.out.println("");
		}
	}

	public void addSection(Section s) {
		String key = s.getRepresentedCourse().getCourseNo() + " - " + s.getSectionNo();
		sections.put(key, s);
		s.setOfferedIn(this);
	}

	public void parseData(String line) {
		String restOfLine = line;
		int index = restOfLine.indexOf("\t");
		String courseNo = restOfLine.substring(0, index);
		restOfLine = restOfLine.substring(index+1);
		index = restOfLine.indexOf("\t");

		String sectionNumber = restOfLine.substring(0, index);
		int sectionNo = Integer.parseInt(sectionNumber);
		restOfLine = restOfLine.substring(index+1);

		index = restOfLine.indexOf("\t");
		String dayOfWeek = restOfLine.substring(0, index);
		restOfLine = restOfLine.substring(index+1);
		index = restOfLine.indexOf("\t");
		String timeOfDay = restOfLine.substring(0, index);
		restOfLine = restOfLine.substring(index+1);
		index = restOfLine.indexOf("\t");
		String room = restOfLine.substring(0, index);

		String capacityValue = restOfLine.substring(index+1);
		int capacity = Integer.parseInt(capacityValue);

		Course c = StudentRegistrationSystem.courseCatalog.findCourse(courseNo);

		Section s = c.scheduleSection(sectionNo, dayOfWeek.charAt(0), timeOfDay, room, capacity);
		String key = courseNo + " - " + s.getSectionNo();
		addSection(s);
	}

	public Section findSection(String fullSectionNo) {
		return (Section) sections.get(fullSectionNo);
	}

	public void parseData2(String line) { }
}
