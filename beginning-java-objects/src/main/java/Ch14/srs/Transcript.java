package Ch14.srs;

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

	public boolean verifyCompletion(Course c) {
		boolean outcome = false;
		return outcome;
	}
}
