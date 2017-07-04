package ch14.srs;

import lombok.Data;

@Data
public class TranscriptEntry {
	private String grade;
	private Student student;
	private Section section;
	private Transcript transcript;

	public TranscriptEntry(Student student, String grade, Section section) {
		this.setStudent(student);
		this.setGrade(grade);
		this.setSection(section);

		Transcript transcript = student.getTranscript();
		this.setTranscript(transcript);
		transcript.addTranscriptEntry(this);
	}

	public static boolean validateGrade(String grade) {
		boolean outcome = false;

		if (grade.equals("F") || grade.equals("I")) {
			outcome = true;
		}

		if (grade.startsWith("A") || grade.startsWith("B") || grade.startsWith("C") || grade.startsWith("D")) {
			if (grade.length() == 1) outcome = true;
			else if (grade.length() == 2) {
				if (grade.endsWith("+") || grade.endsWith("-")) {
					outcome = true;
				}
			}
		}
		return outcome;
	}

	public static boolean passingGrade(String grade) {
		boolean outcome = false;
		if (validateGrade(grade)) {
			if (grade.startsWith("A") || grade.startsWith("B") || grade.startsWith("C") || grade.startsWith("D")) {
				outcome = true;
			}
		}
		return outcome;
	}
}