package com.genius.rockPaperScissors;

public class Scissors {

	public boolean beats(Paper paper) {
		return true;
	}

	public boolean beats(Rock rock) {
		return false;
	}

	public boolean beats(Scissors scissors) {
		return false;
	}
}
