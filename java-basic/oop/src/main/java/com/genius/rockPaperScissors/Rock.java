package com.genius.rockPaperScissors;

public class Rock {

	public boolean beats(Rock rock) {
		return false;
	}

	public boolean beats(Paper paper) {
		return false;
	}

	public boolean beats(Scissors scissors) {
		return true;
	}
}
