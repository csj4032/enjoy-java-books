package com.genius.srs;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Setter
public class Transcript {

	private List<TranscriptEntry> transcriptEntries;
	private Student studentOwner;

	public Transcript(Student student) {
		setStudentOwner(student);
		transcriptEntries = new ArrayList();
	}

	public boolean verifyCompletion(Course course) {
		return transcriptEntries.stream().anyMatch(e -> (e.getSection().isSectionOf(course) && TranscriptEntry.passingGrade(e.getGrade())));
	}

	public void addTranscriptEntry(TranscriptEntry te) {
		transcriptEntries.add(te);
	}

	public void display() {
		log.info("Transcript for:  " + getStudentOwner().toString());
		if (transcriptEntries.isEmpty()) {
			log.info("\t(no entries)");
		} else {
			transcriptEntries.forEach(transcriptEntry -> {
				Section sec = transcriptEntry.getSection();
				Course course = sec.getRepresentedCourse();
				ScheduleOfClasses soc = sec.getOfferedIn();
				log.info("\tSemester:        " + soc.getSemester());
				log.info("\tCourse No.:      " + course.getCourseNo());
				log.info("\tCredits:         " + course.getCredits());
				log.info("\tGrade Received:  " + transcriptEntry.getGrade());
				log.info("\t-----");
			});
		}
	}
}
