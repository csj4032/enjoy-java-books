package com.genius.srs;

import java.util.HashMap;

public class Faculty {

	private HashMap<String, Professor> professors;

	public Faculty() {
		professors = new HashMap<>();
	}

	public void display() {
		professors.values().forEach(p -> p.display());
	}

	public void addProfessor(Professor p) {
		professors.put(p.getSsn(), p);
	}

	public Professor findProfessor(String ssn) {
		return professors.get(ssn);
	}

	public boolean isEmpty() {
		if (professors.size() == 0) return true;
		else return false;
	}
}
