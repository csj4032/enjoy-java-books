package Ch14.srs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Student extends Person {

	private String major;
	private String degree;
	private Transcript transcript;
	private ArrayList<Section> attends;

	public Student(String name, String ssn, String major, String degree) {
		super(name, ssn);
		this.setMajor(major);
		this.setDegree(degree);

		this.setTranscript(new Transcript(this));
		attends = new ArrayList<>();
	}
}
