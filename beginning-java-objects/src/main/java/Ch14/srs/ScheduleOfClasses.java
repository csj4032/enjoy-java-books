package ch14.srs;

import lombok.Data;

import java.util.HashMap;

@Data
public class ScheduleOfClasses {

	private String semester;
	private HashMap<String, Section> sectionsOffered;

	public ScheduleOfClasses(String semester) {
		setSemester(semester);
		sectionsOffered = new HashMap<String, Section>();
	}
}
