package com.genius.rockPaperScissors;

public class Paper {

	public boolean beats(Scissors scissors) {
		return false;
	}

	public boolean beats(Rock rock) {
		return true;
	}

	public boolean beats(Paper paper) {
		return false;
	}
}