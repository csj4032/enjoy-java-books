package ch15.srs;

import java.util.Enumeration;
import java.util.Hashtable;

public class Faculty extends CollectionWrapper {

	private Hashtable professors;

	public Faculty() {
		professors = new Hashtable();
	}

	public void display() {
		System.out.println("Faculty");
		System.out.println("");

		Enumeration enumeration = professors.elements();

		while (enumeration.hasMoreElements()) {
			Professor professor = (Professor) enumeration.nextElement();
			professor.display();
			System.out.println("");
		}
	}

	@Override
	protected void parseData2(String line) {

	}

	@Override
	protected void parseData(String line) {

	}
}
