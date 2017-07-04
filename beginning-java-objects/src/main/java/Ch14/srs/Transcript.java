package ch14.srs;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Transcript {

	private ArrayList<TranscriptEntry> transcriptEntries;
	private Student studentOwner;

	public Transcript(Student student) {
		setStudentOwner(student);
		transcriptEntries = new ArrayList<>();
	}

	public boolean verifyCompletion(Course course) {
		boolean outcome = false;

		for (TranscriptEntry transcriptEntry : transcriptEntries) {
			Section section = transcriptEntry.getSection();
			if (section.isSectionOf(course)) {
				if (TranscriptEntry.passingGrade(transcriptEntry.getGrade())) {
					outcome = true;
					break;
				}
			}
		}

		return outcome;
	}

	public void addTranscriptEntry(TranscriptEntry transcriptEntry) {
		transcriptEntries.add(transcriptEntry);
	}

	public void display() {
		System.out.println("Transcript for:  " + getStudentOwner().toString());

		if (transcriptEntries.size() == 0) {
			System.out.println("\t(no entries)");
		} else for (TranscriptEntry te : transcriptEntries) {
			Section sec = te.getSection();

			Course c = sec.getRepresentedCourse();

			ScheduleOfClasses soc = sec.getOfferedIn();

			System.out.println("\tSemester:        " + soc.getSemester());
			System.out.println("\tCourse No.:      " + c.getCourseNo());
			System.out.println("\tCredits:         " + c.getCredits());
			System.out.println("\tGrade Received:  " + te.getGrade());
			System.out.println("\t-----");
		}
	}
}
