package com.genius.srs;

import lombok.Getter;
import lombok.Setter;

import java.util.Vector;

@Getter
@Setter
public class Transcript {

	private Vector transcriptEntries;
	private Student studentOwner;

	public Transcript(Student s) {
		setStudentOwner(s);
		transcriptEntries = new Vector();
	}

	public boolean verifyCompletion(Course c) {
		boolean outcome = false;
		for (int i = 0; i < transcriptEntries.size(); i++) {
			TranscriptEntry te = (TranscriptEntry) transcriptEntries.elementAt(i);
			Section s = te.getSection();
			if (s.isSectionOf(c)) {
				if (TranscriptEntry.passingGrade(te.getGrade())) {
					outcome = true;
					break;
				}
			}
		}
		return outcome;
	}

	public void addTranscriptEntry(TranscriptEntry te) {
		transcriptEntries.add(te);
	}

	public void display() {
		System.out.println("Transcript for:  " + getStudentOwner().toString());

		if (transcriptEntries.size() == 0) System.out.println("\t(no entries)");

		else for (int i = 0; i < transcriptEntries.size(); i++) {
			TranscriptEntry te = (TranscriptEntry) transcriptEntries.elementAt(i);
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
