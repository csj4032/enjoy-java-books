package ch15.srs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TranscriptEntry {

	private String grade;
	private Student student;
	private Section section;
	private Transcript transcript;

	public TranscriptEntry(Student s, String grade, Section se) {
		setStudent(s);
		setSection(se);
		setGrade(grade);

		Transcript t = s.getTranscript();

		setTranscript(t);
		t.addTranscriptEntry(this);
	}

	public static boolean validateGrade(String grade) {
		boolean outcome = false;

		if (grade.equals("F") || grade.equals("I")) outcome = true;

		if (grade.startsWith("A") || grade.startsWith("B") || grade.startsWith("C") || grade.startsWith("D")) {
			if (grade.length() == 1) outcome = true;
			else if (grade.length() > 2) outcome = false;
			else {
				if (grade.endsWith("+") || grade.endsWith("-")) outcome = true;
				else outcome = false;
			}
		}

		return outcome;
	}

	public static boolean passingGrade(String grade) {
		if (!validateGrade(grade)) return false;
		if (grade.startsWith("A") || grade.startsWith("B") || grade.startsWith("C") || grade.startsWith("D")) return true;
		else return false;
	}
}
